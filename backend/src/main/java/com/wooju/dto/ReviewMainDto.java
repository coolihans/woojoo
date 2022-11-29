package com.wooju.dto;

import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("ReviewMainDto")
public class ReviewMainDto {
	@ApiModelProperty(name="HotReview")
	ArrayList<ReviewDto> Hotreview;
	
	@ApiModelProperty(name="HotProductsreview")
	ArrayList<ReviewDto> Hotproductsreview;
	
	@ApiModelProperty(name="GosuReview")
	ArrayList<ReviewDto> Gosureview;
	
	@ApiModelProperty(name="RecentReview")
	ArrayList<ReviewDto> Recentreview;
}
