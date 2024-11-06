package com.example.studentProject.model;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Admin extends User {
    
    private Integer registrationNumber;

}
