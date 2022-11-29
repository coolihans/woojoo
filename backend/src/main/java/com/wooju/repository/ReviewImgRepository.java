package com.wooju.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wooju.entity.ReviewImg;

public interface ReviewImgRepository extends JpaRepository<ReviewImg, Integer>{
	ArrayList<ReviewImg> findByUserIdAndReviewId(int user,int review);
	void deleteByUserIdAndReviewId(int user,int review);
}
	