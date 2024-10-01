package com.example.studentProject.repository;

import com.example.studentProject.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<ClassRoom, Integer> {
}
