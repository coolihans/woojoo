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
public class LikeReviewID implements Serializable{

	private static final long serialVersionUID = 6544918986014021349L;
	private int user;
	private int review;

}
