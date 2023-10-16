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
@Table(name = "T_SPACE")
public class SpaceEntity {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String S_Key;
	
	@Column(nullable=false)
	private String userId;
	
	@Column(nullable=false)
	private String spaceName;
	
	@Column(nullable=false)
	private String limitPersonNum;
	
	@Column(nullable=false)
	private String area;
	
	@Column(nullable=false)
	private String address;
	
	private String equip;
	//private 
}
