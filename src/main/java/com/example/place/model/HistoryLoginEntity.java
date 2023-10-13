package com.example.place.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "T_HISTORY_LOGIN")
public class HistoryLoginEntity {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String HL_Key;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP) //ex) (2023-10-13 10:16:33)
	private String loginDate;
	
	@Column(nullable=false)
	private String Email;
	
	@Column(nullable=false)
	private String IP;
	
	private String logoutDate;
}
