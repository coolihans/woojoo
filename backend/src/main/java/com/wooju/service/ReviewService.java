package com.wooju.service;

import java.util.ArrayList;

import com.wooju.dto.ReviewDto;
import com.wooju.dto.ReviewMainDto;
import com.wooju.dto.request.ModifyReviewRequestDto;
import com.wooju.dto.request.ReviewRequestDto;
import com.wooju.entity.User;

public interface ReviewService {

	ReviewMainDto getMainPage();
	int createReview(User user, ReviewRequestDto dto) throws Exception;
	ReviewDto getReviewDetail(int id) throws Exception;
	void modifyReview(User user, ModifyReviewRequestDto dto)throws Exception;
	void deleteReview(User user, int id) throws Exception;
	void addLike(int review_id,User user) throws Exception;
	void deleteLike(int review_id, User user) throws Exception;
	ArrayList<ReviewDto> getmorelistPage(String value);
	boolean getlikeCheck(int review_id,User user) throws Exception;

}
