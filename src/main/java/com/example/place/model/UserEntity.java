package com.example.place.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "T_USER")
public class UserEntity {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String U_Key;
	
	@Column(nullable=false)
	private String userName;
	
	@Column(nullable=false, unique = true)
	private String phone;
	
	@Column(nullable=false, unique = true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Column(unique = true)
	private String companyName;
	
	private String companyBossName;
	
	private String companyAddress;
	
}
