package com.example.studentProject.manager;

import com.example.studentProject.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student saveStudent(Student student);

    List<Student> getAllStudents() ;

    Optional<Student> getStudentById(Integer id);

    void deleteStudentById(Integer id);

}
