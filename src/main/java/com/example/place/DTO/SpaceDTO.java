package com.example.place.DTO;

import com.example.place.model.SpaceEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpaceDTO {
	private String spaceName;
	private String limitPersonNum;
	private String area;
	private String address;
	private String equip;
	
	public SpaceDTO(final SpaceEntity entity) {
		this.spaceName = entity.getSpaceName();
		this.limitPersonNum = entity.getLimitPersonNum();
		this.area = entity.getArea();
		this.address = entity.getAddress();
		this.equip = entity.getEquip();
	}
	
	public static SpaceEntity toEntity(final SpaceDTO dto) {
		return SpaceEntity.builder().spaceName(dto.getSpaceName())
									.limitPersonNum(dto.getLimitPersonNum())
									.area(dto.getArea())
									.address(dto.getAddress())
									.equip(dto.getEquip())
									.build();
	}
}
