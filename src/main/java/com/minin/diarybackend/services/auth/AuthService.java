package com.minin.diarybackend.services.auth;

import com.minin.diarybackend.controllers.auth.requests.authenticate.AuthRequest;
import com.minin.diarybackend.controllers.auth.requests.passwords.ChangePasswordRequest;
import com.minin.diarybackend.controllers.auth.requests.passwords.RecoveryPasswordRequest;
import com.minin.diarybackend.controllers.auth.requests.registration.IdentityRegisterRequest;
import com.minin.diarybackend.controllers.auth.requests.registration.StudentRegisterRequest;
import com.minin.diarybackend.controllers.auth.requests.registration.TeacherRegisterRequest;
import com.minin.diarybackend.controllers.auth.responses.TokenResponse;
import com.minin.diarybackend.exceptions.AuthenticationException;
import com.minin.diarybackend.exceptions.BadRequestException;
import com.minin.diarybackend.models.*;
import com.minin.diarybackend.models.types.IdentityType;
import com.minin.diarybackend.repositories.TokenRepository;
import com.minin.diarybackend.services.credentials.ICredentialsService;
import com.minin.diarybackend.services.identity.IIdentityService;
import com.minin.diarybackend.services.student.IStudentService;
import com.minin.diarybackend.services.teacher.ITeacherService;
import com.minin.diarybackend.utils.JwtTokenUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final ICredentialsService credentialsService;
    private final IIdentityService identityService;
    private final IStudentService studentService;
    private final ITeacherService teacherService;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private final TokenRepository tokenRepository;

    @Override
    @Transactional
    public TokenResponse register(IdentityRegisterRequest identityCreateRequest) {

        Identity identity = new Identity();
        String username = "";

        if (credentialsService.isCredentialsExistsByUsername(identityCreateRequest.getUsername())) {
            throw new BadRequestException("this_username_already_exists");
        }

        if (credentialsService.isCredentialsExistsByEmail(identityCreateRequest.getEmail())) {
            throw new BadRequestException("this_email_already_exists");
        }

        if (!identityCreateRequest.getPassword().equals(identityCreateRequest.getConfirmPassword())) {
            throw new BadRequestException("passwords_mismatch");
        }

        switch (identityCreateRequest) {
            case StudentRegisterRequest obj -> {

                String password = passwordEncoder.encode(obj.getPassword());

                Student student = studentService.create(obj);

                identity.setStudent(student);
                identity.setType(IdentityType.STUDENT);
                identityService.save(identity);

                Credentials credentials = credentialsService.create(obj.getUsername(), password, obj.getEmail(), identity);
                username = credentials.getUsername();

            }
            case TeacherRegisterRequest obj -> {

                String password = passwordEncoder.encode(obj.getPassword());

                Teacher teacher = teacherService.create(obj);

                identity.setTeacher(teacher);
                identity.setType(IdentityType.TEACHER);
                identityService.save(identity);

                Credentials credentials = credentialsService.create(obj.getUsername(), password, obj.getEmail(), identity);
                username = credentials.getUsername();

            }
            default -> throw new IllegalStateException("Unexpected value: " + identityCreateRequest);
        }

        UserDetails userDetails = loadUserByUsername(username);

        String accessToken = jwtTokenUtils.generateAccessToken(userDetails);
        String refreshToken = jwtTokenUtils.generateRefreshToken(userDetails);

        saveNewRefreshToken(refreshToken, userDetails.getUsername());

        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    @Transactional
    public TokenResponse authenticate(AuthRequest authRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("wrong_login_or_password");
        }

        UserDetails userDetails = loadUserByUsername(authRequest.getUsername());

        String accessToken = jwtTokenUtils.generateAccessToken(userDetails);
        String refreshToken = jwtTokenUtils.generateRefreshToken(userDetails);

        saveNewRefreshToken(refreshToken, userDetails.getUsername());

        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    @Transactional
    public TokenResponse refreshToken(String token) {

        boolean isTokenValid = tokenRepository.existsTokenByValue(token);

        if (!isTokenValid) {
            throw new RuntimeException();
        }

        String username = jwtTokenUtils.getUsernameFromRefreshToken(token);
        UserDetails userDetails = loadUserByUsername(username);

        String newAccessToken = jwtTokenUtils.generateAccessToken(userDetails);
        String newRefreshToken = jwtTokenUtils.generateRefreshToken(userDetails);

        saveNewRefreshToken(newRefreshToken, username);

        return new TokenResponse(newAccessToken, newRefreshToken);
    }

    @Override
    @Transactional
    public TokenResponse changePassword(UUID identityId, ChangePasswordRequest changePasswordRequest) {

        Credentials credentials = credentialsService.findByIdentityId(identityId);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            changePasswordRequest.getOldPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("wrong_old_password");
        }

        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmNewPassword())) {
            throw new BadRequestException("passwords_mismatch");
        }

        credentials.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        credentialsService.update(credentials);

        UserDetails userDetails = loadUserByUsername(credentials.getUsername());

        String accessToken = jwtTokenUtils.generateAccessToken(userDetails);
        String refreshToken = jwtTokenUtils.generateRefreshToken(userDetails);

        saveNewRefreshToken(refreshToken, userDetails.getUsername());

        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    @Transactional
    public void passwordRecovery(UUID credentialsId, RecoveryPasswordRequest recoveryPasswordRequest) {

        Credentials credentials = credentialsService.findById(credentialsId);

        if (!recoveryPasswordRequest.getNewPassword().equals(recoveryPasswordRequest.getConfirmNewPassword())) {
            throw new BadRequestException("passwords_mismatch");
        }

        credentials.setPassword(passwordEncoder.encode(recoveryPasswordRequest.getNewPassword()));
        credentialsService.update(credentials);
    }

    private void saveNewRefreshToken(String token, String username) {

        Token toSaveToken = tokenRepository.findTokenByUsername(username)
                .orElse(new Token());

        toSaveToken.setUsername(username);
        toSaveToken.setValue(token);

        tokenRepository.save(toSaveToken);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Credentials credentials = credentialsService.findByUsername(username);

        return new User(
                credentials.getUsername(),
                credentials.getPassword(),
                credentials.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }

}
