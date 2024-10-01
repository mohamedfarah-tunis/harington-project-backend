package com.example.studentProject.repository;

import com.example.studentProject.model.Role;
import com.example.studentProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String username);

    User findByRole(Role role);
}

