package com.wooju.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ReviewLikeRequestDto")
public class ReviewLikeRequestDto {

	@ApiModelProperty(name="review_id", example="1")
	int review_id;
	
}
