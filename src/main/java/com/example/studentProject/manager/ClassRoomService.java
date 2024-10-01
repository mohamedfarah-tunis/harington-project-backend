package com.example.studentProject.manager;

import com.example.studentProject.model.ClassRoom;

import java.util.List;
import java.util.Optional;

public interface ClassRoomService {

    ClassRoom saveClassRoom(ClassRoom classRoom);

    List<ClassRoom> getAllClassRooms();

    Optional<ClassRoom> getClassRoomById(Integer id);

    void deleteClassRoomById(Integer id);

}
