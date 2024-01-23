package com.coldgod.projectfbackend.Authentication.Repositories;

import com.coldgod.projectfbackend.Authentication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
