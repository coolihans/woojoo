package com.wooju.dto.request;


import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ModifyReviewRequestDto")
public class ModifyReviewRequestDto {

	@ApiModelProperty(name="id")
	int id;
	
	
	@ApiModelProperty(name="content", example="내용")
	String content;
	
	@ApiModelProperty(name="img")
	ArrayList<String> img;
	
	@ApiModelProperty(name="별점", example="3.5")
	double star;
}
