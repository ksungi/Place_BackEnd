package com.example.place.service;

import java.util.List;
import java.util.Optional;

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
		return repository.findByS_Key(entity.getS_Key());
	}
	
	public List<SpaceEntity> retrieve(final String userName) {
		return repository.findByUserName(userName);
	}
	
	//UPDATE
	public List<SpaceEntity> update(final SpaceEntity entity) {
		//Validations
		validate(entity);
		if (repository.existsById(entity.getS_Key())) {
			repository.save(entity);
		}else
			throw new RuntimeException("Unknown ID(S_Key)");
		
		return repository.findByS_Key(entity.getS_Key());
	}
	
//	//패싱
//	public List<SpaceEntity> updateTodo(final SpaceEntity entity){
//		//Validations
//		validate(entity);
//		
//		// 테이블에서 id에 해당하는 데이타셋을 가져온다. 
//		final List<SpaceEntity> original = repository.findByS_Key(entity.getS_Key());
//		
//		// orignal 에 담겨진 내용을 space 에 할당하고 값을 변경한다. 
//		if(!original.isEmpty()) {
//			final SpaceEntity space = original.get(0);
//			space.setSpaceName(entity.getSpaceName());
//			space.setLimitPersonNum(entity.getLimitPersonNum());
//			space.setArea(entity.getArea());
//			space.setAddress(entity.getAddress());
//			space.setEquip(entity.getEquip());
//			space.setCategory(entity.getCategory());
//			repository.save(space);
//		}
//		
//		return repository.findByS_Key(entity.getS_Key());
//	}

	
	//DELETE
	public List<SpaceEntity> delete(final SpaceEntity entity) {
		if( repository.existsById( entity.getS_Key() ) )
			repository.deleteById( entity.getS_Key() );
		else
			throw new RuntimeException("Key does not exist");
		
		return repository.findByS_Key(entity.getS_Key());
	}
	
	//Validation
	public void validate(final SpaceEntity entity) {
		if(entity == null) {
			log.warn("Entity can't be null");
			throw new RuntimeException("Entity can't be null");
			
		}
		if(entity.getUserName() == null) {
			log.warn("Unknown user");
			throw new RuntimeException("Unknown user");
		}
	}
}
