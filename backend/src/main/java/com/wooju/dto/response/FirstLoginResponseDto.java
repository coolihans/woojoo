package com.wooju.dto.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FirstLoginResponse")
public class FirstLoginResponseDto extends BaseResponseDto{
	
	@ApiModelProperty(name="유저의 이메일", example="ssafy@ssafy.com")
	String userEmail;
	String userType;
	
	public static FirstLoginResponseDto of(Integer statusCode, String message, String userEmail, String userType) {
		FirstLoginResponseDto res = new FirstLoginResponseDto(statusCode, message, userEmail, userType);
		return res;
	}

	public FirstLoginResponseDto(Integer statusCode, String message, String userEmail, String userType) {
		super(statusCode, message);
		this.userEmail = userEmail;
		this.userType = userType;
	}
}
