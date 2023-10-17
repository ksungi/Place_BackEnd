package com.example.place.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.place.model.UserEntity;
import com.example.place.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public UserEntity create(final UserEntity entity) {
		if(entity == null || entity.getEmail() == null) {
			throw new RuntimeException("Invalid arguments");
		}
		final String email = entity.getEmail();
		if(repository.existsByEmail(email)) {
			log.warn("Email already exists {}", email);
			throw new RuntimeException("Email already exists");
		}
		return repository.save(entity);
	}
	
	public UserEntity getByCredentials (final String email, final String password,
			final PasswordEncoder encoder) {
		
		final UserEntity originalUser = repository.findByEmail(email);
		
		if(originalUser != null && encoder.matches(password, originalUser.getPassword())) {
			return originalUser;
		}
		return null;
	}
	
	
//	public UserEntity getUserEntity(final String eamil) {
//		return repository.findByEmail(eamil);
//	}
//	
//	public void updateUserEntity(final UserEntity userEntity) {
//		repository.save(userEntity);
//	}
}