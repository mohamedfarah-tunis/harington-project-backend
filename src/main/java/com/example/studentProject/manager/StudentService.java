package com.example.studentProject.manager;

import com.example.studentProject.dto.StudentDto;
import com.example.studentProject.model.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    List<Student> getAllStudents() ;

    StudentDto getStudentById(Integer id);

    void deleteStudentById(Integer id);

}
