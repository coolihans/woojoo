package com.wooju.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("ProfileProduct")
public class ProfileProductDto {

	@ApiModelProperty(name="id")
	int id;
	@ApiModelProperty(name="술 이름")
	String name;
	@ApiModelProperty(name="이미지")
	String img;
	@ApiModelProperty(name="도수")
	double alcohol;
	@ApiModelProperty(name="좋아요 수")
	int like;
}
