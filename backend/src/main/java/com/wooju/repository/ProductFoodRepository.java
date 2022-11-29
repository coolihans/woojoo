package com.wooju.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wooju.entity.ProductFood;
import com.wooju.entity.ProductFoodID;

public interface ProductFoodRepository extends JpaRepository<ProductFood, ProductFoodID>{

}
