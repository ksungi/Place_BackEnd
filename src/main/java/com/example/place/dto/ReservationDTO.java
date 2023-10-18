package com.example.place.dto;

import com.example.place.model.ReservationEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO {
	private String R_Key;
	//기본예약정보
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
	
	public ReservationDTO(final ReservationEntity entity) {
		this.R_Key = entity.getR_Key();
		this.receiptNo = entity.getReceiptNo();
		this.rentStart = entity.getRentStart();
		this.rentEnd = entity.getRentEnd();
		this.cancleDate = entity.getCancleDate();
		this.state = entity.getState();
		
		this.spaceName = entity.getSpaceName();
		this.spaceAddress = entity.getSpaceAddress();
		this.price = entity.getPrice();
		
		this.SP_Id = entity.getSP_Id();
		this.SP_Phone = entity.getSP_Phone();
		this.SP_Email = entity.getSP_Email();
		
		this.SU_Id = entity.getSU_Id();
		this.SU_Phone = entity.getSU_Phone();
		this.SU_Email = entity.getSU_Email();
	}	
	public static ReservationEntity toEntity(final ReservationDTO dto) {
		return ReservationEntity.builder()
									.R_Key(dto.getR_Key())
									.receiptNo(dto.getReceiptNo())
									.rentStart(dto.getRentStart())
									.rentEnd(dto.getRentEnd())
									.cancleDate(dto.getCancleDate())
									.state(dto.getState())
									.spaceName(dto.getSpaceName())
									.spaceAddress(dto.getSpaceAddress())
									.price(dto.getPrice())
									.SP_Id(dto.getSP_Id())
									.SP_Phone(dto.getSP_Phone())
									.SP_Email(dto.getSP_Email())
									.SU_Id(dto.getSU_Id())
									.SU_Phone(dto.getSU_Phone())
									.SU_Email(dto.getSU_Email())
									.build();
	}
}
