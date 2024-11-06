package com.example.studentProject.manager.impl;

import com.example.studentProject.dto.StudentDto;
import com.example.studentProject.dto.TeacherDto;
import com.example.studentProject.model.ClassRoom;
import com.example.studentProject.model.Role;
import com.example.studentProject.model.Student;
import com.example.studentProject.model.Teacher;
import com.example.studentProject.repository.ClassroomRepository;
import com.example.studentProject.repository.StudentRepository;
import com.example.studentProject.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class AdminServiceImplTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ClassroomRepository classRoomRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterTeacher() {
        Teacher teacher = new Teacher();
        teacher.setRole(Role.TEACHER);

        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        Teacher savedTeacher = adminService.registerTeacher(teacher);

        assertNotNull(savedTeacher);
        assertEquals(Role.TEACHER, savedTeacher.getRole());
        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    void testRegisterStudent() {
        //Given
        Student student = new Student();
        student.setRole(Role.STUDENT);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        //When
        Student savedStudent = adminService.registerStudent(student);

        //Then
        assertNotNull(savedStudent);
        assertEquals(Role.STUDENT, savedStudent.getRole());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testAssignTeacherToClass() {

        //Given
        Integer teacherId = 1;
        Integer classRoomId = 2;
        Teacher teacher = new Teacher();
        ClassRoom classRoom = new ClassRoom();
        Teacher updatedTeacher = new Teacher();
        TeacherDto teacherDto = new TeacherDto();

        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));
        when(classRoomRepository.findById(classRoomId)).thenReturn(Optional.of(classRoom));
        when(teacherRepository.save(teacher)).thenReturn(updatedTeacher);
        when(modelMapper.map(updatedTeacher, TeacherDto.class)).thenReturn(teacherDto);

        //When
        TeacherDto result = adminService.assignTeacherToClass(teacherId, classRoomId);

        //Then
        assertNotNull(result);
        verify(teacherRepository, times(1)).findById(teacherId);
        verify(classRoomRepository, times(1)).findById(classRoomId);
        verify(teacherRepository, times(1)).save(teacher);
        verify(modelMapper, times(1)).map(updatedTeacher, TeacherDto.class);
    }

    @Test
    void testAssignStudentToClass() {

        //Given
        Integer studentId = 1;
        Integer classRoomId = 2;
        Student student = new Student();
        ClassRoom classRoom = new ClassRoom();
        Student updatedStudent = new Student();
        StudentDto studentDto = new StudentDto();

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(classRoomRepository.findById(classRoomId)).thenReturn(Optional.of(classRoom));
        when(studentRepository.save(student)).thenReturn(updatedStudent);
        when(modelMapper.map(updatedStudent, StudentDto.class)).thenReturn(studentDto);

        //When
        StudentDto result = adminService.assignStudentToClass(studentId, classRoomId);

        //Then
        assertNotNull(result);
        verify(studentRepository, times(1)).findById(studentId);
        verify(classRoomRepository, times(1)).findById(classRoomId);
        verify(studentRepository, times(1)).save(student);
        verify(modelMapper, times(1)).map(updatedStudent, StudentDto.class);
    }

    @Test
    void testAssignTeacherToClass_TeacherNotFound() {

        //Given
        Integer teacherId = 1;
        Integer classRoomId = 2;
        when(teacherRepository.findById(teacherId)).thenReturn(Optional.empty());

        //When
        Exception exception = assertThrows(IllegalStateException.class, () ->
                adminService.assignTeacherToClass(teacherId, classRoomId)
        );

        //Then
        assertEquals("No teacher found", exception.getMessage());
    }
}