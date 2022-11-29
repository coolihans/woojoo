package com.wooju.dto.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("LikeCheckResponse")
public class LikeCheckResponseDto extends BaseResponseDto{
	
	@ApiModelProperty(name="like 여부", example="false")
	boolean likecheck;
	
	public static LikeCheckResponseDto of(Integer statusCode, String message, boolean likecheck) {
		LikeCheckResponseDto res = new LikeCheckResponseDto(statusCode, message, likecheck);
		return res;
	}

	public LikeCheckResponseDto(Integer statusCode, String message,boolean likecheck) {
		super(statusCode, message);
		this.likecheck = likecheck;
	}
}
