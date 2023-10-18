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
	
	//기본예약정보
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP) //ex) (2023-10-13 10:16:33)
	private String receiptNo;
	private String rentStart;
	private String rentEnd;
	private String cancleDate;
	private String state;
	
	//공간
	private String spaceName;
	private String spaceAddress;
	private String price;
	
	//제공자 정보 (Space Provider_SP)
	private String SP_Id;
	private String SP_Phone;
	private String SP_Email;
	
	//이용자 정보 (Space User_SU)
	private String SU_Id;
	private String SU_Phone;
	private String SU_Email;
}
