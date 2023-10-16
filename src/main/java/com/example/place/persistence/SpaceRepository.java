package com.example.place.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.place.model.SpaceEntity;

@Repository
public interface SpaceRepository extends JpaRepository<SpaceEntity, String>{
	
	@Query("select t from SpaceEntity t where t.S_Key = ?1")
	List<SpaceEntity> findByS_Key(String S_Key);

	List<SpaceEntity> findByUserName(String userName);
}
