package com.wooju.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ProductLikeRequestDto")
public class ProductLikeRequestDto {

	@ApiModelProperty(name="product_id", example="1")
	int product_id;
	
}
