package com.example.place.DTO;

import com.example.place.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
	private String userName;
	private String phone;
	private String email;
	private String password;
	
	private String companyName;
	private String companyBossName;
	private String companyAddress;
	
	public UserDTO(final UserEntity entity) {
		this.userName = entity.getUserName();
		this.phone = entity.getPhone();
		this.email = entity.getEmail();
		this.password = entity.getPassword();
		
		this.companyName = entity.getCompanyName();
		this.companyBossName = entity.getCompanyBossName();
		this.companyAddress = entity.getCompanyAddress();
	}
	
	public static UserEntity toEntity(final UserDTO dto) {
		return UserEntity.builder().userName(dto.getUserName())
									.phone(dto.getPhone())
									.email(dto.getEmail())
									.password(dto.getPassword())
									.companyName(dto.getCompanyName())
									.companyBossName(dto.getCompanyBossName())
									.companyAddress(dto.getCompanyAddress())
									.build();
	}
}
