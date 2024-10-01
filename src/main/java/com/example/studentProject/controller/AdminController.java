package com.example.studentProject.controller;


import com.example.studentProject.manager.AdminService;
import com.example.studentProject.model.Student;
import com.example.studentProject.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/v1/admin/register/teacher")
    public ResponseEntity<Teacher> registerTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = adminService.registerTeacher(teacher);
        return ResponseEntity.ok(savedTeacher);
    }

    @PostMapping("/v1/admin/register/student")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student savedStudent = adminService.registerStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PutMapping("/v1/admin/assign/teacher/{teacherId}/class/{classRoomId}")
    public ResponseEntity<Teacher> assignTeacherToClass(@PathVariable Integer teacherId, @PathVariable Integer classRoomId) {
        Teacher updatedTeacher = adminService.assignTeacherToClass(teacherId, classRoomId);
        if (updatedTeacher != null) {
            return ResponseEntity.ok(updatedTeacher);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/v1/admin/assign/student/{studentId}/class/{classRoomId}")
    public ResponseEntity<Student> assignStudentToClass(@PathVariable Integer studentId, @PathVariable Integer classRoomId) {
        Student updatedStudent = adminService.assignStudentToClass(studentId, classRoomId);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        }
        return ResponseEntity.notFound().build();
    }
}
