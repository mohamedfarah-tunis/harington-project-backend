package com.example.studentProject.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeacherDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

    private Integer classId;

}
