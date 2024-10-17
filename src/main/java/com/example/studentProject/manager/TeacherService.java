package com.example.studentProject.manager;

import com.example.studentProject.dto.TeacherDto;
import com.example.studentProject.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    Teacher saveTeacher(Teacher teacher);

    List<Teacher> getAllTeachers();

    TeacherDto getTeacherById(Integer id);

    void deleteTeacherById(Integer id);

}
