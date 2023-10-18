package com.example.place.dto;

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
	private String S_Key;
	
	private String userName;
	private String spaceName;
	private String limitPersonNum;
	private String area;
	private String address;
	private String equip;
	private String price;
	private String category;
	
	public SpaceDTO(final SpaceEntity entity) {
		this.S_Key = entity.getS_Key();
		this.userName = entity.getUserName();
		this.spaceName = entity.getSpaceName();
		this.limitPersonNum = entity.getLimitPersonNum();
		this.area = entity.getArea();
		this.address = entity.getAddress();
		this.equip = entity.getEquip();
		this.price = entity.getPrice();
		this.category = entity.getCategory();
	}
	
	public static SpaceEntity toEntity(final SpaceDTO dto) {
		return SpaceEntity.builder()
									.S_Key(dto.getS_Key())
									.userName(dto.getUserName())
									.spaceName(dto.getSpaceName())
									.limitPersonNum(dto.getLimitPersonNum())
									.area(dto.getArea())
									.address(dto.getAddress())
									.equip(dto.getEquip())
									.price(dto.getPrice())
									.category(dto.getCategory())
									.build();
	}
}
