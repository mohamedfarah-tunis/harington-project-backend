package com.example.studentProject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Teacher extends User {

    private Integer id;

    private String subject;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassRoom classRoom;

}
