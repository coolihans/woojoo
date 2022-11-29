package com.wooju.dto.request;

import java.time.LocalDate;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SignUpRequestDto")
public class SignUpRequestDto {

	@ApiModelProperty(name="email", example="ssafy@gmail.com")
	String email;
	
	@ApiModelProperty(name="img")
	String img;
	
	@ApiModelProperty(name="usertype", example="google")
	String usertype;
	
	@ApiModelProperty(name="nickname", example="닉네임이에여")
	String nickname;
	
	@ApiModelProperty(name="birthdate", example="2000-01-01")
	LocalDate birthdate;
	
	@ApiModelProperty(name="gender", example="F")
	String gender;
}
