package com.wooju.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SurveyRequestDto")
public class SurveyRequestDto {

	@ApiModelProperty(name="술의 종류", example="증류주")
	String type;
	
	@ApiModelProperty(name="question1")
	int question1;
	
	@ApiModelProperty(name="question2")
	int question2;
	
	@ApiModelProperty(name="question3")
	int question3;
	
	@ApiModelProperty(name="question4")
	int question4;
	
	@ApiModelProperty(name="question5")
	int question5;
	
	@ApiModelProperty(name="question6")
	int question6;
}
