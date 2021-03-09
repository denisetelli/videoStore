package com.videoStore.javaBackEnd.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videoStore.javaBackEnd.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
}
