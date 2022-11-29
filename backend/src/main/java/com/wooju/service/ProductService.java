package com.wooju.service;

import java.util.ArrayList;

import com.wooju.dto.ProductReviewDto;
import com.wooju.dto.request.ProductListRequestDto;
import com.wooju.entity.User;

public interface ProductService {

	void addLike(int product_id,User user) throws Exception;
	void deleteLike(int product_id, User user) throws Exception;
	Object getlist(ProductListRequestDto dto);
	Object getdetail(int id);
	ArrayList<ProductReviewDto> getReviewList(int id);
	boolean getlikeCheck(int product_id,User user) throws Exception;

}
