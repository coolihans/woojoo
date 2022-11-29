package com.wooju.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor	
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ProductFoodID implements Serializable{

	private static final long serialVersionUID = 2820801480599558687L;
	private int product;
	private int food;

}
