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
public class Maker {
	
	@Id
    private int id;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "official_name",nullable=false)
	private String official_name;
	
	@Column(name = "ceo_name", nullable = false)
	private String ceo_name;
	
	@Column(name ="address", nullable =false)
	private String address;
	
	@OneToMany(mappedBy = "maker")
	private List<ProductMaker> productmakers;
	

}
