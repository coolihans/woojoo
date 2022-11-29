package com.wooju.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Food {
	
	@Id
    private int id;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "img",nullable=false)
	private String img;
	
	@OneToMany(mappedBy = "food")
	private List<ProductFood> productfoods;

	

}
