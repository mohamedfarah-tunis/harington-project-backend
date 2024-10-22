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

    public List<StudentDto> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentDto> studentDtoList = studentList.stream().map(student -> {
            StudentDto dto = new StudentDto();
            dto.setId(student.getId());
            dto.setFirstName(student.getFirstName());
            dto.setLastName(student.getLastName());
            dto.setEmail(student.getEmail());
            dto.setRole(String.valueOf(student.getRole()));
            dto.setClassId(student.getClassRoom().getId());
            return dto;
        }).toList();

        return studentDtoList;
    }

    public StudentDto getStudentById(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no Student found with this id " + id));
        return mapper.map(student, StudentDto.class);
    }

    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

}
