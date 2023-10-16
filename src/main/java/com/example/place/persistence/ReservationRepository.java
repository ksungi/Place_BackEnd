package com.example.place.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.place.model.ReservationEntity;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, String>{

	List<ReservationEntity> findByReceipNo(String receipNo);

	boolean existsByReceipNo(String receipNo);

	void deleteByReceipNo(String receipNo);

}
