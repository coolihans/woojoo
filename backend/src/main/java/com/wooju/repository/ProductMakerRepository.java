package com.wooju.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wooju.entity.ProductMaker;
import com.wooju.entity.ProductMakerID;

public interface ProductMakerRepository extends JpaRepository<ProductMaker, ProductMakerID>{

}
