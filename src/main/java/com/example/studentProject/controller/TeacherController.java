package com.example.studentProject.controller;

import com.example.studentProject.dto.TeacherDto;
import com.example.studentProject.manager.TeacherService;
import com.example.studentProject.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/v1/teachers")
    public ResponseEntity<Teacher> createOrUpdateTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherService.saveTeacher(teacher);
        return ResponseEntity.ok(savedTeacher);
    }

    @GetMapping("/v1/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/v1/teachers/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Integer id) {
        TeacherDto teacher = teacherService.getTeacherById(id);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }

    @DeleteMapping("/v1/teachers/{id}")
    public ResponseEntity<Void> deleteTeacherById(@PathVariable Integer id) {
        teacherService.deleteTeacherById(id);
        return ResponseEntity.noContent().build();
    }

}
