package com.wooju.dto.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("LoginResponse")
public class LoginResponseDto extends BaseResponseDto{
	
	@ApiModelProperty(name="JWT 인증 토큰", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
	String accesstoken;
	
	public static LoginResponseDto of(Integer statusCode, String message, String authorization) {
		LoginResponseDto res = new LoginResponseDto(statusCode, message, authorization);
		return res;
	}

	public LoginResponseDto(Integer statusCode, String message, String accesstoken) {
		super(statusCode, message);
		this.accesstoken = accesstoken;
	}
}
