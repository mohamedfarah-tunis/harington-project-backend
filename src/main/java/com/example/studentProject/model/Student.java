package com.example.studentProject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Student extends User {

    private Integer id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassRoom classRoom;

}