package com.example.place.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.place.model.ReservationEntity;
import com.example.place.model.SpaceEntity;
import com.example.place.persistence.ReservationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReservationService {
		
		@Autowired
		private ReservationRepository repository;
		
		//Create
		public List<ReservationEntity> create(final ReservationEntity entity){
			validate(entity);
			log.info(">>create entrance + reposi="+this.repository+"/entity="+entity);
			repository.save(entity);
			log.info(">>Repository.save통과 /reposi="+this.repository+"/entity="+entity);
			return repository.findByR_Key(entity.getR_Key());
		}
		
		//UPDATE
		public List<ReservationEntity> update(final ReservationEntity entity){
			validate(entity);
			
			if(repository.existsById(entity.getR_Key())) 
				repository.save(entity);
			else
				throw new RuntimeException("Unkown ID(R_Key)");
			
			return repository.findByR_Key(entity.getR_Key());
		}
		
		//DELETE
		public List<ReservationEntity> delete(final ReservationEntity entity){
			if(repository.existsById(entity.getR_Key()))
				repository.deleteById(entity.getR_Key());
			else
				throw new RuntimeException("Key does not exist"); 
				
			return repository.findByR_Key(entity.getR_Key());
		}
		
		//Validation
		public void validate(final ReservationEntity entity) {
			if(entity == null) {
				log.warn("Entity can't be null");
				throw new RuntimeException("Entity can't be null");
				
			}
			if(entity.getSU_Id() == null) {
				log.warn("Unknown Reservation");
				throw new RuntimeException("Unknown Reservation");
			}
		}
}
