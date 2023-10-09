package com.example.diarybackend.services.auth;

import com.example.diarybackend.controllers.auth.requests.IdentityRegisterRequest;
import com.example.diarybackend.models.Credentials;
import com.example.diarybackend.models.Identity;
import com.example.diarybackend.models.Student;
import com.example.diarybackend.models.Teacher;
import com.example.diarybackend.models.types.IdentityType;
import com.example.diarybackend.services.credentials.ICredentialsService;
import com.example.diarybackend.services.identity.IIdentityService;
import com.example.diarybackend.services.student.IStudentService;
import com.example.diarybackend.services.teacher.ITeacherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    @Override
    @Transactional
    public Identity register(IdentityRegisterRequest identityCreateRequest) {

        Identity identity = new Identity();

        switch (identityCreateRequest) {
            case IdentityRegisterRequest.StudentCreateRequest obj -> {

                String password = passwordEncoder.encode(obj.password());

                Student student = studentService.create(obj);

                identity.setStudentId(student.getId());
                identity.setType(IdentityType.STUDENT);
                identityService.save(identity);

                credentialsService.create(obj.username(), password, obj.email(), identity);

            }
            case IdentityRegisterRequest.TeacherCreateRequest obj -> {

                String password = passwordEncoder.encode(obj.password());

                Teacher teacher = teacherService.create(obj);

                identity.setTeacherId(teacher.getId());
                identity.setType(IdentityType.TEACHER);
                identityService.save(identity);

                credentialsService.create(obj.username(), password, obj.email(), identity);


            }
            default -> throw new IllegalStateException("Unexpected value: " + identityCreateRequest);
        }

        return identity;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Credentials credentials = credentialsService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user_not_found"));

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
