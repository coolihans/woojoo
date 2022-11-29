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
public class LikeProductID implements Serializable{

	private static final long serialVersionUID = 1248878806198083754L;
	private int user;
	private int product;

}
