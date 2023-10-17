package com.example.place.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.place.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{

	boolean existsByEmail(String email);

	UserEntity findByEmailAndPassword(String email, String password);

}
