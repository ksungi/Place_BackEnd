package com.example.place.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.place.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	
	Boolean existsByEmail(String email);
	UserEntity findByEmail(String email);
}
