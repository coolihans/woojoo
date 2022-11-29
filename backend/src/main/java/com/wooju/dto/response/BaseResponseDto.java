package com.wooju.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 서버 요청에대한 기본 응답값(바디) 정의.
 */
@Getter
@Setter
@ApiModel("BaseResponseDto")
@NoArgsConstructor
public class BaseResponseDto {
	
	@ApiModelProperty(name="�쓳�떟 肄붾뱶", example = "200")
	Integer statusCode = null;
	@ApiModelProperty(name="�쓳�떟 硫붿떆吏�", example = "�젙�긽")
	String message = null;
	
	public BaseResponseDto(Integer statusCode, String message){
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public static BaseResponseDto of(Integer statusCode, String message) {
		BaseResponseDto body = new BaseResponseDto(statusCode, message);
		return body;
	}
}
