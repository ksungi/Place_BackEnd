package com.example.place.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.place.model.SpaceEntity;

@Repository
public interface SpaceRepository extends JpaRepository<SpaceEntity, String>{
	
}
