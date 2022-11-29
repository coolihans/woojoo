package com.wooju.dto.response;


import com.wooju.dto.ReviewMainDto;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("ReviewMainResponse")
public class ReviewMainResponseDto extends BaseResponseDto{
	
	ReviewMainDto dto;
	
	public static ReviewMainResponseDto of(Integer statusCode, String message, ReviewMainDto dto) {
		ReviewMainResponseDto res = new ReviewMainResponseDto(statusCode, message, dto);
		return res;
	}

	public ReviewMainResponseDto(Integer statusCode, String message, ReviewMainDto dto) {
		super(statusCode, message);
		this.dto = dto;
	}
}
