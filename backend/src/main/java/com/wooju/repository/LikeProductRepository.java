package com.wooju.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wooju.entity.LikeProduct;
import com.wooju.entity.LikeProductID;

@Repository
public interface LikeProductRepository extends JpaRepository<LikeProduct, LikeProductID>{	
	Long countByProductIdAndUserId(int productid,int userid);
	Long countByProductId(int productid);
	void deleteByProductIdAndUserId(int productid,int userid);
	

}
