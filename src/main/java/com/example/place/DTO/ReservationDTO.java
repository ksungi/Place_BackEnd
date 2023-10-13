package com.example.place.DTO;

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

	private String reservedDate;
	private String rentStart;
	private String rentEnd;
	private String price;
	private String cancleDate;
	private String state;
	
	public ReservationDTO(final ReservationEntity entity) {
		this.reservedDate = entity.getReservedDate();
		this.rentStart = entity.getRentStart();
		this.rentEnd = entity.getRentEnd();
		this.price = entity.getPrice();
		this.cancleDate = entity.getCancleDate();
		this.state = entity.getState();
	}
	
	public static ReservationEntity toEntity(final ReservationDTO dto) {
		return ReservationEntity.builder().reservedDate(dto.getReservedDate())
									.rentStart(dto.getRentStart())
									.rentEnd(dto.getRentEnd())
									.price(dto.getPrice())
									.cancleDate(dto.getCancleDate())
									.state(dto.getState())
									.build();
	}
}
