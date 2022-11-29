package com.wooju.dto.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RecomResponse")
public class RecomResponseDto extends BaseResponseDto{
	
	@ApiModelProperty(name="object")
	Object obj;
	
	public static RecomResponseDto of(Integer statusCode, String message, Object obj) {
		RecomResponseDto res = new RecomResponseDto(statusCode, message, obj);
		return res;
	}

	public RecomResponseDto(Integer statusCode, String message, Object obj) {
		super(statusCode, message);
		this.obj = obj;
	}
}
