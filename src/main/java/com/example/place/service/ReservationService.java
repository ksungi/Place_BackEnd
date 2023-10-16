package com.example.place.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.place.model.ReservationEntity;
import com.example.place.persistence.ReservationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository repository;
	
	//CREATE
		public List<ReservationEntity> create(final ReservationEntity entity){
			//Validations
			validate(entity);
			
			repository.save(entity);
			
			return repository.findByReceipNo(entity.getReceipNo()); 
		}
		
		
		public List<ReservationEntity> retrieve(final String receipNo) {
			return repository.findByReceipNo(receipNo);
		}
		
		//UPDATE
		public List<ReservationEntity> update(final ReservationEntity entity) {
			//Validations
			validate(entity);
			if (repository.existsByReceipNo(entity.getReceipNo())) {
				repository.save(entity);
			}else
				throw new RuntimeException("Unknown id");
			
			return repository.findByReceipNo(entity.getReceipNo());
		}
		
		//DELETE
		public List<ReservationEntity> delete(final ReservationEntity entity) {
			if( repository.existsByReceipNo( entity.getReceipNo() ) )
				repository.deleteByReceipNo( entity.getReceipNo() );
			else
				throw new RuntimeException("id does not exist");
			
			return repository.findByReceipNo(entity.getReceipNo());
		}
		
		//Validation
		public void validate(final ReservationEntity entity) {
			if(entity == null) {
				log.warn("Entity can't be null");
				throw new RuntimeException("Entity can't be null");
				
			}
			if(entity.getReceipNo() == null) {
				log.warn("Unknown user");
				throw new RuntimeException("Unknown user");
			}
		}
}
