package com.wooju.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wooju.entity.LikeReview;
import com.wooju.entity.LikeReviewID;

@Repository
public interface LikeReviewRepository extends JpaRepository<LikeReview, LikeReviewID>{

	long countByReviewId(int review_id);
	long countByReviewIdAndUserId(int review_id, int id);
	void deleteByReviewIdAndUserId(int review_id, int id);	


}
