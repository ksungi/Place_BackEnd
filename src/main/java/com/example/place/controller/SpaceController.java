package com.example.place.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.place.dto.ResponseDTO;
import com.example.place.dto.SpaceDTO;
import com.example.place.model.SpaceEntity;
import com.example.place.service.SpaceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("space")
public class SpaceController {
	
	@Autowired
	private SpaceService service;
	
	@PostMapping
	public ResponseEntity<?>createSpace(
			//@AuthenticationPrincipal String userId, 
			@RequestBody SpaceDTO dto){
		try {
			log.info(">>createSpace entrance");
			
			//dto를 이용해 테이블에 저장하기 위한 entity를 생성
			SpaceEntity entity = SpaceDTO.toEntity(dto);
			log.info(">>dto => entity ok!");
			
			//entity userId를 임시로 지정
			//entity.setS_Key(null);
			entity.setUserName("temporaryID");
			
			//service.create를 통해 repository에 entity를 저장한다.
			List<SpaceEntity> entities = service.create(entity);
			//Optional<SpaceEntity> entities = service.create(entity);
			log.info("Log:serivce.create ok!");

			
			//entities를 dtos로 스트림 변환
			List<SpaceDTO> dtos = entities.stream().map(SpaceDTO::new).collect(Collectors.toList());
			log.info(">>entity => dtos OK!");
			
			//Response DTO 생성
			ResponseDTO<SpaceDTO> response = ResponseDTO.<SpaceDTO>builder().data(dtos).build();
			log.info(">>responseDTO OK!");
			
			//HTTP Status200 상태로 res 를 전송
			return ResponseEntity.ok().body(response);
			
		}catch(Exception e){
			String error = e.getMessage();
			ResponseDTO<SpaceDTO> response = ResponseDTO.<SpaceDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?>retrieveSpace(
			//@AuthenticationPrincipal String userId
			){
		String temporaryUserId ="temporaryID";
		List<SpaceEntity> entities = service.retrieve(temporaryUserId);
		List<SpaceDTO> dtos = entities.stream().map(SpaceDTO::new).collect(Collectors.toList());
		
		ResponseDTO<SpaceDTO> response = ResponseDTO.<SpaceDTO>builder().data(dtos).build();
		
		//HTTP Status200 상태로 res를 전송
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/update") 
	public ResponseEntity<?>update(@RequestBody SpaceDTO dto){
		try {
			//dto를 이용해 테이블에 저장하기 위한 entity를 생성
			SpaceEntity entity = SpaceDTO.toEntity(dto);
			
			//entity userId를 임시로 지정
			entity.setUserName("temporaryID");
			
			//service.create를 통해 repository에 entity를 저장
			//이때 넘어오는 값이 없을 수도 있으므로 List가 아닌 Optional로 한다.
			List<SpaceEntity> entities = service.update(entity);
			
			//entities를 dtos로 스트림 변환
			List<SpaceDTO> dtos = entities.stream().map(SpaceDTO::new).collect(Collectors.toList());
			
			//ResponseDTO를 생성
			ResponseDTO<SpaceDTO> response =ResponseDTO.<SpaceDTO>builder().data(dtos).build();
			
			//HTTP Status200 상태로 response를 전송
			return ResponseEntity.ok().body(response);
			
		}catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<SpaceDTO> response = ResponseDTO.<SpaceDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateSpace(
			//@AuthenticationPrincipal String userId, 
			@RequestBody SpaceDTO dto){
		try {
			//dto를 이용해 테이블에 저장하기 위한 entity를 생성
			SpaceEntity entity = SpaceDTO.toEntity(dto);
			
			//entity userId를 임시로 지정
			entity.setUserName("temporaryID");
			
			//service.create를 통해 repository에 entity를 저장
			List<SpaceEntity> entities = service.update(entity);
			
			//entities를 dtos로 스트림변환
			List<SpaceDTO> dtos = entities.stream().map(SpaceDTO::new).collect(Collectors.toList());
			
			//ResponseDTO 생성
			ResponseDTO<SpaceDTO> response = ResponseDTO.<SpaceDTO>builder().data(dtos).build();
			
			//HTTP Status200 상태로 res 전송
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			String error = e.getMessage();
			ResponseDTO<SpaceDTO> response = ResponseDTO.<SpaceDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteSpace(
			//@AuthenticationPrincipal String userId, 
			@RequestBody SpaceDTO dto){
		try {
			SpaceEntity entity = SpaceDTO.toEntity(dto);
			
			// entity userId를 임시로 지정
			entity.setUserName("temporary");
			List<SpaceEntity> entities = service.delete(entity);
			
			//entities를 dtos로 스트림 변환
			List<SpaceDTO> dtos = entities.stream().map(SpaceDTO::new).collect(Collectors.toList());
			
			//ResponseDTO를 생성
			ResponseDTO<SpaceDTO> response = ResponseDTO.<SpaceDTO>builder().data(dtos).build();
			
			//HTTP Status200 상태로 res 전송
			return ResponseEntity.ok().body(response);
			
		}catch(Exception e) {
			String error = e.getMessage();
			ResponseDTO<SpaceDTO> response = ResponseDTO.<SpaceDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
}
