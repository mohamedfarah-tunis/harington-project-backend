package com.example.studentProject.manager.impl;


import com.example.studentProject.dto.StudentDto;
import com.example.studentProject.dto.TeacherDto;
import com.example.studentProject.manager.AdminService;
import com.example.studentProject.model.ClassRoom;
import com.example.studentProject.model.Role;
import com.example.studentProject.model.Student;
import com.example.studentProject.model.Teacher;
import com.example.studentProject.repository.ClassroomRepository;
import com.example.studentProject.repository.StudentRepository;
import com.example.studentProject.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final TeacherRepository teacherRepository;

    private final StudentRepository studentRepository;

    private final ClassroomRepository classRoomRepository;

    private final ModelMapper modelMapper;

    public Teacher registerTeacher(Teacher teacher) {
        teacher.setRole(Role.TEACHER);
        return teacherRepository.save(teacher);
    }

    public Student registerStudent(Student student) {
        student.setRole(Role.STUDENT);
        return studentRepository.save(student);
    }

    public TeacherDto assignTeacherToClass(Integer teacherId, Integer classRoomId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalStateException("No teacher found"));

        ClassRoom classRoom = classRoomRepository.findById(classRoomId)
                .orElseThrow(() -> new IllegalStateException("No classroom found"));

        teacher.setClassRoom(classRoom);
        Teacher updatedTeacher = teacherRepository.save(teacher);

        return modelMapper.map(updatedTeacher, TeacherDto.class);
    }

    public StudentDto assignStudentToClass(Integer studentId, Integer classRoomId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("no student found with this id" + studentId));
        ClassRoom classRoom = classRoomRepository.findById(classRoomId)
                .orElseThrow(() -> new IllegalStateException("no classRoom found with this id" + classRoomId));

        student.setClassRoom(classRoom);
        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentDto.class);
    }

}
