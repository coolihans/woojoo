package com.wooju.dto;

import java.time.LocalDate;
import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("ProductReviewDto")
public class ProductReviewDto {

	

	@ApiModelProperty(name="nickname")
	int id;
	@ApiModelProperty(name="nickname")
	String nickname;
	@ApiModelProperty(name="user_img")
	String user_img;
	@ApiModelProperty(name="img")
	ArrayList<String> img;
	@ApiModelProperty(name="gosu")
	boolean gosu;	
	@ApiModelProperty(name="content")
	String content;
	@ApiModelProperty(name="time")
	LocalDate time;
	@ApiModelProperty(name="star")
	double star;
	@ApiModelProperty(name="like")
	int like;
}
