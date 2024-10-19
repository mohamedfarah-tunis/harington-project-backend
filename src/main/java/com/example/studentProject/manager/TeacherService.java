package com.example.studentProject.manager;

import com.example.studentProject.dto.TeacherDto;
import com.example.studentProject.dto.view.IUserView;
import com.example.studentProject.model.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher saveTeacher(Teacher teacher);

    List<IUserView> getAllTeachers();

    TeacherDto getTeacherById(Integer id);

    void deleteTeacherById(Integer id);

}
