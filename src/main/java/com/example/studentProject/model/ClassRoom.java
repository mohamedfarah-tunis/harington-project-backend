package com.example.studentProject.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="classroom")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "classRoom")
    private List<Student> students;

    @OneToMany(mappedBy = "classRoom")
    private List<Teacher> teachers;
}
