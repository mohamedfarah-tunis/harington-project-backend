package com.example.studentProject.manager;

import com.example.studentProject.dto.JwtAuthenticationResponse;
import com.example.studentProject.dto.RefreshTokenRequest;
import com.example.studentProject.dto.SignInRequest;
import com.example.studentProject.dto.SignUpRequest;
import com.example.studentProject.model.User;

public interface AuthenticationService {

    User signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
