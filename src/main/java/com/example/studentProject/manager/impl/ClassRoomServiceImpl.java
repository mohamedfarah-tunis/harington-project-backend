package com.example.studentProject.manager.impl;


import com.example.studentProject.manager.ClassRoomService;
import com.example.studentProject.model.ClassRoom;
import com.example.studentProject.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {

    private final ClassroomRepository classRoomRepository;


    public ClassRoom saveClassRoom(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    public List<ClassRoom> getAllClassRooms() {
        return classRoomRepository.findAll();
    }

    public Optional<ClassRoom> getClassRoomById(Integer id) {
        return classRoomRepository.findById(id);
    }


    public void deleteClassRoomById(Integer id) {
        classRoomRepository.deleteById(id);
    }

}
