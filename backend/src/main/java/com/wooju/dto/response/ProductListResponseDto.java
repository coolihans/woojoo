package com.wooju.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ProductListResponse")
public class ProductListResponseDto extends BaseResponseDto{
	
	@ApiModelProperty(name="object")
	Object object;
	
	public static ProductListResponseDto of(Integer statusCode, String message, Object object) {
		ProductListResponseDto res = new ProductListResponseDto(statusCode, message, object);
		return res;
	}

	public ProductListResponseDto(Integer statusCode, String message, Object object) {
		super(statusCode, message);
		this.object = object;
	}
}
