package com.example.studentProject.manager;

import com.example.studentProject.model.Student;
import com.example.studentProject.model.Teacher;

public interface AdminService {

    Teacher registerTeacher(Teacher teacher);

    Student registerStudent(Student student);

    Teacher assignTeacherToClass(Integer teacherId, Integer classRoomId);

    Student assignStudentToClass(Integer studentId, Integer classRoomId);

}
