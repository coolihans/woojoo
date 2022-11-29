package com.wooju.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.wooju.dto.ProductReviewDto;
import com.wooju.dto.request.ProductListRequestDto;
import com.wooju.entity.LikeProduct;
import com.wooju.entity.Product;
import com.wooju.entity.Review;
import com.wooju.entity.ReviewImg;
import com.wooju.entity.User;
import com.wooju.exception.LikeException;
import com.wooju.exception.ProductNotFoundException;
import com.wooju.exception.ReviewNotFoundException;
import com.wooju.repository.LikeProductRepository;
import com.wooju.repository.ProductRepository;
import com.wooju.repository.ReviewImgRepository;
import com.wooju.repository.ReviewRepository;

@Service
public class ProductServiceImpl implements ProductService{
	@Value("${cloud.fastapi.address}")
    private String address;
	
	@Autowired
	LikeProductRepository likeProductRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	ReviewImgRepository reviewImgRepository;
	
	@Override
	@Transactional
	public void addLike(int product_id, User user) throws Exception {
		long check =likeProductRepository.countByProductIdAndUserId(product_id, user.getId());
		if(check != 0) throw new LikeException();
		Optional<Product> product= productRepository.findById(product_id);
		if(!product.isPresent()) throw new ProductNotFoundException();
		LikeProduct likeproduct= LikeProduct.builder()
					.user(user)
					.product(product.get())
					.build();
		likeProductRepository.save(likeproduct);
		long num=likeProductRepository.countByProductId(product_id);
		product.get().setLike((int)num);
		
	}

	@Override
	@Transactional
	public void deleteLike(int product_id, User user) throws Exception {
		long check = likeProductRepository.countByProductIdAndUserId(product_id, user.getId());
		if(check == 0 )throw new LikeException();
		Optional<Product> product= productRepository.findById(product_id);
		if(!product.isPresent()) throw new ProductNotFoundException();
		likeProductRepository.deleteByProductIdAndUserId(product_id, user.getId());
		long num=likeProductRepository.countByProductId(product_id);
		product.get().setLike((int)num);
		
	}

	@Override
	public Object getlist(ProductListRequestDto dto) {
		WebClient webclient = WebClient.builder()
				.baseUrl(address)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		
		if(dto.getTypes().isEmpty() || dto.getTypes().get(0).equals("")) {
			ResponseEntity<Object> result=webclient.get().
					uri(uriBuilder-> uriBuilder
						    .path("/product/")
						    .queryParam("alcohol", dto.getAlcohol())
						    .queryParam("isAward", dto.isAward())
						    .queryParam("page", dto.getPage())
						    .queryParam("size", dto.getSize())
						    .queryParam("search",dto.getKeyword())
						    .build())
					.retrieve()
					.toEntity(Object.class)
					.block();
			return (result.getBody());
		}else {
			ResponseEntity<Object> result=webclient.get().
				uri(uriBuilder-> uriBuilder
					    .path("/product/")
					    .queryParam("types", dto.getTypes())
					    .queryParam("alcohol", dto.getAlcohol())
					    .queryParam("isAward", dto.isAward())
					    .queryParam("page", dto.getPage())
					    .queryParam("size", dto.getSize())
					    .queryParam("search",dto.getKeyword())
					    .build())
				.retrieve()
				.toEntity(Object.class)
				.block();
			return (result.getBody());
		}
	}

	@Override
	public Object getdetail(int id) {
		WebClient webclient = WebClient.builder()
				.baseUrl(address)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		
		ResponseEntity<Object> result=webclient.get().
				uri("/product/"+id)
				.retrieve()
				.toEntity(Object.class)
				.block();
		
		
		
		return (result.getBody());
	}

	@Override
	public ArrayList<ProductReviewDto> getReviewList(int id) {
		ArrayList<Review> reviewTemp= reviewRepository.findByProductId(id);
		ArrayList<ProductReviewDto> list=new ArrayList<ProductReviewDto>();
		
		for(Review review:reviewTemp) {
			ProductReviewDto dto= ProductReviewDto.builder()
					.id(review.getId())
					.user_img(review.getUser().getImg())
					.gosu(review.getUser().isGosu())
					.nickname(review.getUser().getNickname())
					.content(review.getContent())
					.star(review.getStar())
					.time(review.getTime())
					.like(review.getLike())
					.build();
			dto.setImg(new ArrayList<String>());
			ArrayList<ReviewImg> imgs= reviewImgRepository.findByUserIdAndReviewId(review.getUser().getId(), review.getId());
			for(ReviewImg img:imgs) {
				dto.getImg().add(img.getImg());
			}
			list.add(dto);
		}
		return list;
	}

	@Override
	public boolean getlikeCheck(int product_id,User user) throws Exception {
		if(!productRepository.findById(product_id).isPresent()) throw new ProductNotFoundException();
		long num=likeProductRepository.countByProductIdAndUserId(product_id, user.getId());
		if(num==1)return true;
		else if(num==0) return false;
		else throw new LikeException();
	}




}
