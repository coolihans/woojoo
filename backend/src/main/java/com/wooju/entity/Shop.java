package com.wooju.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "image", nullable = true, length = 500)
	private String image;
	
	@Column(name = "url", nullable = false, length = 500)
	private String url;
	
	@Column(name = "mall_name", nullable = false)
	private String mall_name;
	
	@Column(name = "price", nullable = false)
	private int price;
	
	
	
}
