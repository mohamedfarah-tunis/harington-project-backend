package com.example.studentProject.manager.impl;


import com.example.studentProject.manager.StudentService;
import com.example.studentProject.model.Student;
import com.example.studentProject.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

}
