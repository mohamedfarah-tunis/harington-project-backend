package com.example.studentProject.manager.impl;

import com.example.studentProject.dto.TeacherDto;
import com.example.studentProject.manager.TeacherService;
import com.example.studentProject.model.Teacher;
import com.example.studentProject.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final ModelMapper mapper;

    private final TeacherRepository teacherRepository;

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public TeacherDto getTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no teacher found with this id " + id));
        return mapper.map(teacher, TeacherDto.class);
    }

    public void deleteTeacherById(Integer id) {
        teacherRepository.deleteById(id);
    }

}
