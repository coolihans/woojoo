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
public class ProductMakerID implements Serializable{

	private static final long serialVersionUID = 6699092804800036431L;
	private int product;
	private int maker;

}
