package com.example.place.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.place.DTO.ResponseDTO;
import com.example.place.DTO.UserDTO;
import com.example.place.model.UserEntity;
import com.example.place.security.TokenProvider;
import com.example.place.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private TokenProvider tokenProvider;
	
private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/signup")
	public ResponseEntity<?>registerUser(@RequestBody UserDTO dto){
		try {
			UserEntity user = UserEntity.builder()
										.userName(dto.getUserName())
										.phone(dto.getPhone())
										.email(dto.getEmail())
										.password(passwordEncoder.encode(dto.getPassword()))
										.companyName(dto.getCompanyName())
										.companyBossName(dto.getCompanyBossName())
										.companyAddress(dto.getCompanyAddress())
										.build();
			
			UserEntity registeredUser = service.create(user);
			UserDTO responseUserDTO = dto.builder()
										.U_Key(registeredUser.getU_Key())
										.userName(registeredUser.getUserName())
										.phone(registeredUser.getPhone())
										.email(registeredUser.getEmail())
										.companyName(registeredUser.getCompanyName())
										.companyBossName(registeredUser.getCompanyBossName())
										.companyAddress(registeredUser.getCompanyAddress())
										.build();
			
			return ResponseEntity.ok().body(responseUserDTO);
		}catch(Exception e) {
			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody UserDTO dto){
		UserEntity user = service.getByCredentials(dto.getEmail(), dto.getUserName(), dto.getPassword(), passwordEncoder);
		
		if(user != null) {
			final String token = tokenProvider.create(user);
			final UserDTO responseUserDTO = UserDTO.builder()
													.userName(dto.getUserName())
													.phone(dto.getPhone())
													.email(dto.getEmail())
													.companyName(dto.getCompanyName())
													.companyBossName(dto.getCompanyBossName())
													.companyAddress(dto.getCompanyAddress())
													.token(token)
													.build();
			
			return ResponseEntity.ok().body(responseUserDTO);
		}else {
			ResponseDTO responseDTO = ResponseDTO.builder()
											.error("Login failed")
											.build();
			
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	
	@PostMapping("/myroom")
	public void Myroom(@RequestBody UserDTO dto) {
		//user 원래 정보 //보내온 Email로 DB내의 정보를 찾으니 Email은 변경불가
		String before_userset = service.getUserEntity(dto.getEmail().toString()).getU_Key();
		
		//업데이트 정보 & 기존 정보를 담을 임시 개체
		UserEntity after_userset = UserEntity.builder()
												.U_Key(before_userset)
												.email(dto.getEmail())
												.userName(dto.getUserName())
												.phone(dto.getPhone())
												.companyName(dto.getCompanyName())
												.companyBossName(dto.getCompanyBossName())
												.companyAddress(dto.getCompanyAddress())
												.password(passwordEncoder.encode(dto.getPassword()))
												.build();
		//업데이트
		service.updateUserEntity(after_userset);
	}

}
