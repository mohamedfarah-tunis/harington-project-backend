package com.example.studentProject.manager.impl;


import com.example.studentProject.manager.AdminService;
import com.example.studentProject.model.ClassRoom;
import com.example.studentProject.model.Role;
import com.example.studentProject.model.Student;
import com.example.studentProject.model.Teacher;
import com.example.studentProject.repository.ClassroomRepository;
import com.example.studentProject.repository.StudentRepository;
import com.example.studentProject.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final TeacherRepository teacherRepository;

    private final StudentRepository studentRepository;

    private final ClassroomRepository classRoomRepository;

    public Teacher registerTeacher(Teacher teacher) {
        teacher.setRole(Role.TEACHER);
        return teacherRepository.save(teacher);
    }

    public Student registerStudent(Student student) {
        student.setRole(Role.STUDENT);
        return studentRepository.save(student);
    }

    public Teacher assignTeacherToClass(Integer teacherId, Integer classRoomId) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);
        Optional<ClassRoom> classRoomOpt = classRoomRepository.findById(classRoomId);

        if (teacherOpt.isPresent() && classRoomOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();
            ClassRoom classRoom = classRoomOpt.get();
            teacher.setClassRoom(classRoom);
            return teacherRepository.save(teacher);
        }
        return null;
    }

    public Student assignStudentToClass(Integer studentId, Integer classRoomId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<ClassRoom> classRoomOpt = classRoomRepository.findById(classRoomId);

        if (studentOpt.isPresent() && classRoomOpt.isPresent()) {
            Student student = studentOpt.get();
            ClassRoom classRoom = classRoomOpt.get();
            student.setClassRoom(classRoom);
            return studentRepository.save(student);
        }
        return null;
    }

}
