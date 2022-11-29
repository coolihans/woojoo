package com.wooju.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(LikeProductID.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeProduct {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	

}
