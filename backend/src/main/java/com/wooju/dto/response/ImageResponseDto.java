package com.wooju.dto.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ImageResponse")
public class ImageResponseDto extends BaseResponseDto{
	
	@ApiModelProperty(name="img url", example="http://wooju-bucket/...")
	String img;
	
	public static ImageResponseDto of(Integer statusCode, String message, String img) {
		ImageResponseDto res = new ImageResponseDto(statusCode, message, img);
		return res;
	}

	public ImageResponseDto(Integer statusCode, String message, String img) {
		super(statusCode, message);
		this.img = img;
	}
}
