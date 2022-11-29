package com.wooju.dto.response;


import com.wooju.dto.ReviewDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("ReviewDetailResponse")
public class ReviewDetailResponseDto extends BaseResponseDto{
	
	@ApiModelProperty(name="review")
	ReviewDto review;
	
	public static ReviewDetailResponseDto of(Integer statusCode, String message, ReviewDto review) {
		ReviewDetailResponseDto res = new ReviewDetailResponseDto(statusCode, message, review);
		return res;
	}

	public ReviewDetailResponseDto(Integer statusCode, String message, ReviewDto review) {
		super(statusCode, message);
		this.review = review;
	}
}
