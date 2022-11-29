package com.wooju.dto.response;

import java.util.ArrayList;

import com.wooju.dto.ProductReviewDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ProductDetailResponse")
public class ProductDetailResponseDto extends BaseResponseDto{
	
	@ApiModelProperty(name="object")
	Object object;
	@ApiModelProperty(name="review")
	ArrayList<ProductReviewDto> list;
	
	public static ProductDetailResponseDto of(Integer statusCode, String message, Object object,ArrayList<ProductReviewDto> list) {
		ProductDetailResponseDto res = new ProductDetailResponseDto(statusCode, message, object,list);
		return res;
	}

	public ProductDetailResponseDto(Integer statusCode, String message, Object object,ArrayList<ProductReviewDto> list) {
		super(statusCode, message);
		this.object = object;
		this.list=list;
	}
}
