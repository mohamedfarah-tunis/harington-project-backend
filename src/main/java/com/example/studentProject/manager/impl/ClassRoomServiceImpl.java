package com.example.studentProject.manager.impl;


import com.example.studentProject.dto.ClassroomDto;
import com.example.studentProject.manager.ClassRoomService;
import com.example.studentProject.model.ClassRoom;
import com.example.studentProject.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {

    private final ClassroomRepository classRoomRepository;

    private final ModelMapper mapper;

    public ClassRoom saveClassRoom(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    public List<ClassRoom> getAllClassRooms() {
        return classRoomRepository.findAll();
    }

    public ClassroomDto getClassRoomById(Integer id) {
        ClassRoom classRoom = classRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no classsroom found"));
        return mapper.map(classRoom, ClassroomDto.class);
    }


    public void deleteClassRoomById(Integer id) {
        classRoomRepository.deleteById(id);
    }

}
