package com.example.studentProject.model;


import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Admin extends User {
    
    private Integer registrationNumber;

}
