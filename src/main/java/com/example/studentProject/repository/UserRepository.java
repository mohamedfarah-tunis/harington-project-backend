package com.example.studentProject.repository;

import com.example.studentProject.dto.view.IUserView;
import com.example.studentProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String username);

    @Query(value = "SELECT u.id, u.first_name, u.last_name, u.email, u.role " +
            "FROM _user u " +
            "WHERE u.role = :role", nativeQuery = true)
    List<IUserView> findAllUsersByRole(@Param("role") String role);
}

