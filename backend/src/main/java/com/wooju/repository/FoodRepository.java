package com.wooju.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wooju.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Integer>{

}
