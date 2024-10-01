package com.example.studentProject.controller;


import com.example.studentProject.manager.StudentService;
import com.example.studentProject.manager.impl.StudentServiceImpl;
import com.example.studentProject.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/v1/students")
    public ResponseEntity<Student> createOrUpdateTeacher(@RequestBody Student student) {
        Student savedStudent= studentService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/v1/students")
    public List<Student> getAllStudent() {
        return studentService.getAllStudents();
    }

    @GetMapping("/v1/students/{id}")
    public ResponseEntity<Student> getTeacherById(@PathVariable Integer id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/v1/students/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

}
