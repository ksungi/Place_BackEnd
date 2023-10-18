package com.example.place.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.place.dto.ReservationDTO;
import com.example.place.dto.ResponseDTO;
import com.example.place.dto.SpaceDTO;
import com.example.place.model.ReservationEntity;
import com.example.place.model.SpaceEntity;
import com.example.place.model.UserEntity;
import com.example.place.persistence.ReservationRepository;
import com.example.place.service.ReservationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("reservation")
public class ReservationController {
	
	@Autowired
	private ReservationService service;
	
	@Autowired
	private ReservationRepository repository;
	
	@PostMapping
	public ResponseEntity<?>createSpace(
			@AuthenticationPrincipal String SU_Id,
			@RequestBody ReservationDTO dto){
		try {
			log.info(">>createReserve entrance");
			  
				  ////////////////
				 // Entity 생성 //
				////////////////

			ReservationEntity entity = ReservationDTO.toEntity(dto);
			log.info(">>dto => entity ok!");

		//기본예약정보
			//예약상태 - 취소/승인대기/승인
			// 기본적인것은 입력받을 예정
			entity.setReceiptNo("2023-10-18 12:55:33");
			entity.setState("승인대기");
			
			//공간정보
			SpaceEntity spaceEnti = repository.findBySpaceName(entity.getSpaceName());
//			entity.setSpaceName(spaceEnti.getSpaceName());
			entity.setSpaceAddress(spaceEnti.getAddress());
			//entity.setPrice(spaceEnti.getPrice());
			entity.setPrice("10000원"); //임시
			
			//제공자정보
			UserEntity SPEnti= repository.findBySP_Id(spaceEnti.getUserName());
			entity.setSP_Id(spaceEnti.getUserName());
			entity.setSP_Phone(SPEnti.getPhone());
			entity.setSP_Email(SPEnti.getEmail());			
			
			//이용자정보
			UserEntity SUEnti= repository.findBySP_Id(SU_Id);
			entity.setSU_Id(SU_Id);
			entity.setSU_Phone(SUEnti.getPhone());
			entity.setSU_Email(SUEnti.getEmail());	
			log.info("Log: Entity 기록완료!");
			
			//service.create를 통해 repository에 entity를 저장한다.
			List<ReservationEntity> entities = service.create(entity);
			log.info("Log:serivce.create ok!");
			
			//entities를 dtos로 스트림 변환
			List<ReservationDTO> dtos = entities.stream().map(ReservationDTO::new).collect(Collectors.toList());
			log.info(">>entity => dtos OK!");
			
			//Response DTO 생성
			ResponseDTO<ReservationDTO> response = ResponseDTO.<ReservationDTO>builder().data(dtos).build();
			log.info(">>responseDTO OK!");
			
			//HTTP Status200 상태로 res 를 전송
			return ResponseEntity.ok().body(response);
			
		}catch(Exception e){
			String error = e.getMessage();
			ResponseDTO<ReservationDTO> response = ResponseDTO.<ReservationDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?>retrieveReservation(@AuthenticationPrincipal String userName){
		List<ReservationEntity> entities = service.retrieve(userName);
		List<ReservationDTO> dtos = entities.stream().map(ReservationDTO::new).collect(Collectors.toList());
		
		ResponseDTO<ReservationDTO> response = ResponseDTO.<ReservationDTO>builder().data(dtos).build();
		
		//HTTP Status200 상태로 res를 전송
		return ResponseEntity.ok().body(response);
	}
	
	

}//end of func
