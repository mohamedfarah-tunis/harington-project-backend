package com.example.studentProject.controller;


import com.example.studentProject.manager.ClassRoomService;
import com.example.studentProject.model.ClassRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClassRoomController {

    private final ClassRoomService classRoomService;

    @PostMapping("/v1/classrooms")
    public ResponseEntity<ClassRoom> createOrUpdateClassRoom(@RequestBody ClassRoom classRoom) {
        ClassRoom saveClassRoom = classRoomService.saveClassRoom(classRoom);
        return ResponseEntity.ok(saveClassRoom);
    }

    @GetMapping("/v1/classrooms")
    public List<ClassRoom> getAllClassRoom() {
        return classRoomService.getAllClassRooms();
    }

    @GetMapping("/v1/classrooms/{id}")
    public ResponseEntity<ClassRoom> getClassRoomById(@PathVariable Integer id) {
        Optional<ClassRoom> classRoom = classRoomService.getClassRoomById(id);
        return classRoom.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/v1/classrooms/{id}")
    public ResponseEntity<Void> deleteClassRoomById(@PathVariable Integer id) {
        classRoomService.deleteClassRoomById(id);
        return ResponseEntity.noContent().build();
    }
}
