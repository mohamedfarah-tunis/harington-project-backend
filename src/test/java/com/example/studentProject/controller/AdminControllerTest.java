package com.example.studentProject.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.studentProject.dto.StudentDto;
import com.example.studentProject.dto.TeacherDto;
import com.example.studentProject.manager.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testAssignStudentToClass() throws Exception {
        Integer studentId = 1;
        Integer classRoomId = 2;
        StudentDto studentDto = new StudentDto();
        studentDto.setId(studentId);

        when(adminService.assignStudentToClass(studentId, classRoomId)).thenReturn(studentDto);

        mockMvc.perform(put("/api/v1/admin/assign/student/{studentId}/class/{classRoomId}", studentId, classRoomId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(adminService, times(1)).assignStudentToClass(studentId, classRoomId);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testAssignTeacherToClass() throws Exception {
        Integer teacherId = 1;
        Integer classRoomId = 2;
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacherId);

        when(adminService.assignTeacherToClass(teacherId, classRoomId)).thenReturn(teacherDto);

        mockMvc.perform(put("/api/v1/admin/assign/teacher/{teacherId}/class/{classRoomId}", teacherId, classRoomId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(adminService, times(1)).assignTeacherToClass(teacherId, classRoomId);
    }

}