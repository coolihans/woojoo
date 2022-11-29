package com.wooju.dto.request;


import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ProductListRequestDto")
public class ProductListRequestDto {


	@ApiModelProperty(name="types")
	ArrayList<String> types;
	
	@ApiModelProperty(name="alcohol")
	float alcohol;
	
	@ApiModelProperty(name="isAward", example="내용")
	boolean isAward;
	
	@ApiModelProperty(name="keyword")
	String keyword;
	
	@ApiModelProperty(name="page")
	int page;
	
	@ApiModelProperty(name="size")
	int size;
	
}
