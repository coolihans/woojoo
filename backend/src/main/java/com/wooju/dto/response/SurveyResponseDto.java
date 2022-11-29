package com.wooju.dto.response;

import com.wooju.dto.SurveyDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SurveyResponse")
public class SurveyResponseDto extends BaseResponseDto{
	
	@ApiModelProperty(name="Suervey")
	SurveyDto dto;
	
	public static SurveyResponseDto of(Integer statusCode, String message, SurveyDto dto) {
		SurveyResponseDto res = new SurveyResponseDto(statusCode, message, dto);
		return res;
	}

	public SurveyResponseDto(Integer statusCode, String message, SurveyDto dto) {
		super(statusCode, message);
		this.dto = dto;
	}
}
