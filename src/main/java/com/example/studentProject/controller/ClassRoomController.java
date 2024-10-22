package com.example.studentProject.controller;

import com.example.studentProject.dto.ClassroomDto;
import com.example.studentProject.manager.ClassRoomService;
import com.example.studentProject.model.ClassRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClassRoomController {

    private final ClassRoomService classRoomService;

    @PreAuthorize("@authenticationServiceImpl.hasRole(authentication, 'ADMIN')")
    @PostMapping("/v1/classrooms")
    public ResponseEntity<ClassRoom> createOrUpdateClassRoom(@RequestBody ClassRoom classRoom) {
        ClassRoom saveClassRoom = classRoomService.saveClassRoom(classRoom);
        return ResponseEntity.ok(saveClassRoom);
    }


    @PreAuthorize("@authenticationServiceImpl.hasRole(authentication, 'ADMIN')")
    @GetMapping("/v1/classrooms")
    public List<ClassRoom> getAllClassRoom() {
        return classRoomService.getAllClassRooms();
    }

    @PreAuthorize("@authenticationServiceImpl.hasRole(authentication, 'ADMIN')")
    @GetMapping("/v1/classrooms/{id}")
    public ResponseEntity<ClassroomDto> getClassRoomById(@PathVariable Integer id) {
        ClassroomDto classRoom = classRoomService.getClassRoomById(id);
        return ResponseEntity.status(HttpStatus.OK).body(classRoom);
    }

    @PreAuthorize("@authenticationServiceImpl.hasRole(authentication, 'ADMIN')")
    @DeleteMapping("/v1/classrooms/{id}")
    public ResponseEntity<Void> deleteClassRoomById(@PathVariable Integer id) {
        classRoomService.deleteClassRoomById(id);
        return ResponseEntity.ok().build();
    }
}
