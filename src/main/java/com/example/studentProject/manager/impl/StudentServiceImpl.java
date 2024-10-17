package com.example.studentProject.manager.impl;

import com.example.studentProject.dto.StudentDto;
import com.example.studentProject.manager.StudentService;
import com.example.studentProject.model.Student;
import com.example.studentProject.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final ModelMapper mapper;

    private final StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentDto getStudentById(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no Student found with this id " + id));
        return mapper.map(student, StudentDto.class);
    }

    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

}
