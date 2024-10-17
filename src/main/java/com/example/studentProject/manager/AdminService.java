package com.example.studentProject.manager;

import com.example.studentProject.dto.StudentDto;
import com.example.studentProject.dto.TeacherDto;
import com.example.studentProject.model.Student;
import com.example.studentProject.model.Teacher;

public interface AdminService {

    Teacher registerTeacher(Teacher teacher);

    Student registerStudent(Student student);

    TeacherDto assignTeacherToClass(Integer teacherId, Integer classRoomId);

    StudentDto assignStudentToClass(Integer studentId, Integer classRoomId);

}
