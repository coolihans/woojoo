package com.wooju.dto.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ModifyProfileRequestDto")
public class ModifyProfileRequestDto {

	@ApiModelProperty(name="nickname", example="닉네임")
	String nickname;
	@ApiModelProperty(name="image", example="https://bucket/abced.com")
	String img;
	
}
