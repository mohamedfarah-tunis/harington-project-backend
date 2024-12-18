package com.example.studentProject.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String subject;
}
