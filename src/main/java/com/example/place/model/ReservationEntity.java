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
@Table(name = "T_RESERVATION")
public class ReservationEntity {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String R_Key;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP) //ex) (2023-10-13 10:16:33)
	private String reservedDate;
	
	@Column(nullable=false)
	private String rentStart;
	
	@Column(nullable=false)
	private String rentEnd;
	
	@Column(nullable=false)
	private String price;
	
	private String cancleDate;
	
	private String state;
}
