package com.example.studentProject.manager.impl;

import com.example.studentProject.dto.ClassroomDto;
import com.example.studentProject.model.ClassRoom;
import com.example.studentProject.repository.ClassroomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class ClassRoomServiceImplTest {

    @Autowired
    private ClassRoomServiceImpl classRoomService;

    @Autowired
    private ClassroomRepository classroomRepository;


    @BeforeEach
    public void setUp() {
        classroomRepository.deleteAll();  // Nettoyer les données avant chaque test
    }

    @Test
    public void testSaveClassRoom() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName("Class A");

        ClassRoom savedClassRoom = classRoomService.saveClassRoom(classRoom);

        assertNotNull(savedClassRoom.getId());
        assertEquals("Class A", savedClassRoom.getName());
    }

    @Test
    public void testGetClassRoomById() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName("Class B");
        classRoom = classroomRepository.save(classRoom);

        ClassroomDto dto = classRoomService.getClassRoomById(classRoom.getId());

        assertNotNull(dto);
        assertEquals("Class B", dto.getName());
    }

    @Test
    public void testDeleteClassRoomById() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName("Class C");
        classRoom = classroomRepository.save(classRoom);

        classRoomService.deleteClassRoomById(classRoom.getId());

        assertFalse(classroomRepository.findById(classRoom.getId()).isPresent());
    }

}