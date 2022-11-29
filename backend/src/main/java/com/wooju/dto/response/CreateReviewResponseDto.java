package com.wooju.dto.response;


import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("CreateReviewResponse")
public class CreateReviewResponseDto extends BaseResponseDto{
	
	int id;
	
	public static CreateReviewResponseDto of(Integer statusCode, String message, int id) {
		CreateReviewResponseDto res = new CreateReviewResponseDto(statusCode, message, id);
		return res;
	}

	public CreateReviewResponseDto(Integer statusCode, String message, int id) {
		super(statusCode, message);
		this.id = id;
	}
}
