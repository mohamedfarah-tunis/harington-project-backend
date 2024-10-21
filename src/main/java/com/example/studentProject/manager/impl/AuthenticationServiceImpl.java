package com.example.studentProject.manager.impl;

import com.example.studentProject.dto.JwtAuthenticationResponse;
import com.example.studentProject.dto.RefreshTokenRequest;
import com.example.studentProject.dto.SignInRequest;
import com.example.studentProject.dto.SignUpRequest;
import com.example.studentProject.manager.AuthenticationService;
import com.example.studentProject.manager.JWTService;
import com.example.studentProject.manager.StudentService;
import com.example.studentProject.model.*;
import com.example.studentProject.repository.AdminRepository;
import com.example.studentProject.repository.StudentRepository;
import com.example.studentProject.repository.TeacherRepository;
import com.example.studentProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;


    public User signUp(SignUpRequest signUpRequest) {

        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = null;

        switch (Role.valueOf(signUpRequest.getRole().toUpperCase())) {
            case TEACHER:
                Teacher teacher = new Teacher();
                teacher.setSubject(signUpRequest.getSubject());
                user = teacher;
                break;

            case STUDENT:
                Student student = new Student();
                user = student;
                break;

            case ADMIN:
                Admin admin = new Admin();
                user = admin;
                break;

            default:
                throw new IllegalArgumentException("Unknown role: " + signUpRequest.getRole());
        }

        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.valueOf(signUpRequest.getRole().toUpperCase()));

        return userRepository.save(user);
    }


    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                signInRequest.getPassword()));

        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("invalid email or password"));
        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(token);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("no user found with this email"));
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

    public boolean hasRole(Authentication authentication, String role) {
        System.out.println("Checking role for: " + role);
        System.out.println("User authorities: " + authentication.getAuthorities());
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }

}
