package com.example.studentProject.manager.impl;

import com.example.studentProject.dto.StudentDto;
import com.example.studentProject.dto.TeacherDto;
import com.example.studentProject.dto.view.IUserView;
import com.example.studentProject.exception.TeacherNotFoundException;
import com.example.studentProject.manager.TeacherService;
import com.example.studentProject.model.Student;
import com.example.studentProject.model.Teacher;
import com.example.studentProject.model.User;
import com.example.studentProject.repository.TeacherRepository;
import com.example.studentProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final ModelMapper mapper;

    private final TeacherRepository teacherRepository;

    private final UserRepository userRepository;

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDto> teacherList = teachers.stream().map(teacher -> {
            TeacherDto dto = new TeacherDto();
            dto.setId(teacher.getId());
            dto.setFirstName(teacher.getFirstName());
            dto.setLastName(teacher.getLastName());
            dto.setEmail(teacher.getEmail());
            dto.setRole(String.valueOf(teacher.getRole()));
            dto.setSubject(teacher.getSubject());
            if (teacher.getClassRoom() != null) {
                dto.setClassId(teacher.getClassRoom().getId());
            } else {
                dto.setClassId(null);
            }
            return dto;
        }).toList();

        return teacherList;
    }

    public TeacherDto getTeacherById(Integer id) {
        User teacher = userRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("No teacher found with this id: " + id));
        return mapper.map(teacher, TeacherDto.class);
    }

    public void deleteTeacherById(Integer id) {
        teacherRepository.deleteById(id);
    }

}
