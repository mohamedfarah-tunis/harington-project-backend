package com.example.studentProject.controller;

import com.example.studentProject.dto.StudentDto;
import com.example.studentProject.dto.TeacherDto;
import com.example.studentProject.manager.AdminService;
import com.example.studentProject.model.Student;
import com.example.studentProject.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("@authenticationServiceImpl.hasRole(authentication, 'ADMIN')")
    @PostMapping("/v1/admin/register/teacher")
    public ResponseEntity<Teacher> registerTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = adminService.registerTeacher(teacher);
        return ResponseEntity.ok(savedTeacher);
    }

    @PreAuthorize("@authenticationServiceImpl.hasRole(authentication, 'ADMIN')")
    @PostMapping("/v1/admin/register/student")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student savedStudent = adminService.registerStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PreAuthorize("@authenticationServiceImpl.hasRole(authentication, 'ADMIN')")
    @PutMapping("/v1/admin/assign/teacher/{teacherId}/class/{classRoomId}")
    public ResponseEntity<TeacherDto> assignTeacherToClass(@PathVariable Integer teacherId, @PathVariable Integer classRoomId) {
        TeacherDto updatedTeacher = adminService.assignTeacherToClass(teacherId, classRoomId);
        if (updatedTeacher != null) {
            return ResponseEntity.ok(updatedTeacher);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("@authenticationServiceImpl.hasRole(authentication, 'ADMIN')")
    @PutMapping("/v1/admin/assign/student/{studentId}/class/{classRoomId}")
    public ResponseEntity<StudentDto> assignStudentToClass(@PathVariable Integer studentId, @PathVariable Integer classRoomId) {
        StudentDto updatedStudent = adminService.assignStudentToClass(studentId, classRoomId);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        }
        return ResponseEntity.notFound().build();
    }
}
