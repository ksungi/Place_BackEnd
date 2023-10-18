package com.example.place.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.place.model.ReservationEntity;
import com.example.place.model.SpaceEntity;
import com.example.place.model.UserEntity;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, String> {
	
	@Query("select t from ReservationEntity t where t.R_Key = ?1")
	List<ReservationEntity> findByR_key(String r_Key);
	
	@Query("select t from ReservationEntity t where t.R_Key = ?1")
	List<ReservationEntity> findByR_Key(String R_Key);
	
	@Query("select t from SpaceEntity t where t.spaceName = ?1")
	SpaceEntity findBySpaceName(String spaceName);
	
	@Query("select t from UserEntity t where t.userName = ?1")
	UserEntity findBySP_Id(String userName);
	
	@Query("select t from UserEntity t where t.userName = ?1")
	UserEntity findBySU_Id(String userName);

	

	

}
