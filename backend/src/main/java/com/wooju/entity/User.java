package com.wooju.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "img", nullable = false)
	private String img;
	
	@Column(name = "email", nullable = false, length = 80)
	private String email;
	
	@Column(name = "usertype", nullable = false)
	private String usertype;
	
	@Column(name = "nickname", nullable = false)
	private String nickname;
	
	@Column(name = "birthdate", nullable = false)
	private LocalDate birthdate;
	
	@Column(name = "gender", nullable = false)
	private String gender;
	
	@Column(name = "review_count",nullable = false)
	private int review_count;
	
	@Column(name = "gosu", nullable=false)
	private boolean gosu;
	
	@Column(name="type", nullable=false)
	private String type;
	
	@Column(name = "question1", nullable =false)
	private int question1;
	
	@Column(name = "question2", nullable =false)
	private int question2;
	
	@Column(name = "question3", nullable =false)
	private int question3;
	
	@Column(name = "question4", nullable =false)
	private int question4;
	
	@Column(name = "question5", nullable =false)
	private int question5;
	
	@Column(name = "question6", nullable =false)
	private int question6;
	
	@OneToMany(mappedBy = "user")
	private List<LikeProduct> likeproducts;
	
	@OneToMany(mappedBy = "user")
	private List<Review> reviews;
	
	@OneToMany(mappedBy = "user")
	private List<ReviewImg> reviewimgs;
	
	@OneToMany(mappedBy = "user")
	private List<LikeReview> reviewreviews;
	
}
