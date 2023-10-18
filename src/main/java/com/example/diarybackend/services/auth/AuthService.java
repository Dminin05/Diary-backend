package com.example.diarybackend.services.auth;

import com.example.diarybackend.controllers.auth.requests.AuthRequest;
import com.example.diarybackend.controllers.auth.requests.IdentityRegisterRequest;
import com.example.diarybackend.controllers.auth.requests.StudentRegisterRequest;
import com.example.diarybackend.controllers.auth.requests.TeacherRegisterRequest;
import com.example.diarybackend.controllers.auth.responses.TokenResponse;
import com.example.diarybackend.exceptions.AuthenticationException;
import com.example.diarybackend.exceptions.BadRequestException;
import com.example.diarybackend.models.*;
import com.example.diarybackend.models.types.IdentityType;
import com.example.diarybackend.repositories.TokenRepository;
import com.example.diarybackend.services.credentials.ICredentialsService;
import com.example.diarybackend.services.identity.IIdentityService;
import com.example.diarybackend.services.student.IStudentService;
import com.example.diarybackend.services.teacher.ITeacherService;
import com.example.diarybackend.utils.JwtTokenUtils;
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
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
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
