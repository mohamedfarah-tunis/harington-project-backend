package com.example.studentProject.controller;

import com.example.studentProject.dto.StudentDto;
import com.example.studentProject.dto.view.IUserView;
import com.example.studentProject.manager.StudentService;
import com.example.studentProject.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PreAuthorize("@authenticationServiceImpl.hasRole(authentication, 'ADMIN') or @authenticationServiceImpl.hasRole(authentication, 'STUDENT')")
    @PostMapping("/v1/students")
    public ResponseEntity<Student> createOrUpdateTeacher(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PreAuthorize("@authenticationServiceImpl.hasRole(authentication, 'ADMIN') or @authenticationServiceImpl.hasRole(authentication, 'STUDENT')")
    @GetMapping("/v1/students")
    public List<StudentDto> getAllStudent() {
        return studentService.getAllStudents();
    }

    @PreAuthorize("@authenticationServiceImpl.hasRole(authentication, 'ADMIN') or @authenticationServiceImpl.hasRole(authentication, 'STUDENT')")
    @GetMapping("/v1/students/{id}")
    public ResponseEntity<StudentDto> getTeacherById(@PathVariable Integer id) {
        StudentDto student = studentService.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PreAuthorize("@authenticationServiceImpl.hasRole(authentication, 'ADMIN') or @authenticationServiceImpl.hasRole(authentication, 'STUDENT')")
    @DeleteMapping("/v1/students/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

}
