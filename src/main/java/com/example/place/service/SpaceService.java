package com.example.place.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.place.model.SpaceEntity;
import com.example.place.persistence.SpaceRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpaceService {
	
	@Autowired
	private SpaceRepository repository;
	
	//Create
	public List<SpaceEntity> create(final SpaceEntity entity) {
		//Validations
		validate(entity);
		
		repository.save(entity);
		
		return repository.findByUserId(entity.getUserId());
	}
	
	public List<SpaceEntity> retrieve(final String userId) {
		return repository.findByUserId(userId);
	}
	
	//UPDATE
	public List<SpaceEntity> update(final SpaceEntity entity) {
		//Validations
		validate(entity);
				
		if (repository.existsById(entity.getUserId())) {
			repository.save(entity);
		}else
			throw new RuntimeException("Unknown id");
		
		return repository.findByUserId(entity.getUserId());
	}
	
	//DELETE
	public List<SpaceEntity> delete(final SpaceEntity entity) {
		if( repository.existsById( entity.getUserId() ) )
			repository.deleteById( entity.getUserId() );
		else
			throw new RuntimeException("ID does not exist");
		
		return repository.findByUserId(entity.getUserId());
	}
	
	//Validation
	public void validate(final SpaceEntity entity) {
		if(entity == null) {
			log.warn("Entity can't be null");
			throw new RuntimeException("Entity can't be null");
			
		}
		if(entity.getUserId() == null) {
			log.warn("Unknown user");
			throw new RuntimeException("Unknown user");
		}
	}
}
