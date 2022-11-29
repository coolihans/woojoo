package com.wooju.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wooju.dto.ReviewDto;
import com.wooju.dto.ReviewMainDto;
import com.wooju.dto.request.ModifyReviewRequestDto;
import com.wooju.dto.request.ReviewRequestDto;
import com.wooju.entity.LikeReview;
import com.wooju.entity.Product;
import com.wooju.entity.Review;
import com.wooju.entity.ReviewImg;
import com.wooju.entity.User;
import com.wooju.exception.DifferentUserException;
import com.wooju.exception.LikeException;
import com.wooju.exception.ProductNotFoundException;
import com.wooju.exception.ReviewNotFoundException;
import com.wooju.repository.LikeProductRepository;
import com.wooju.repository.LikeReviewRepository;
import com.wooju.repository.ProductRepository;
import com.wooju.repository.ReviewImgRepository;
import com.wooju.repository.ReviewRepository;
import com.wooju.repository.UserRepository;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	S3upload s3upload;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	LikeProductRepository likeProductRepository;
	
	@Autowired
	LikeReviewRepository likeReviewRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ReviewImgRepository reviewImgRepository;
	
	@Override
	public ReviewMainDto getMainPage() {
		ReviewMainDto dto =ReviewMainDto.builder().build();
		
		
		dto.setHotreview(new ArrayList<ReviewDto>());
		ArrayList<Review> hotreview = reviewRepository.findTop3ByTimeBetweenOrderByLikeDesc(LocalDate.now().minusDays(2),LocalDate.now());
		
		for(Review review: hotreview) {			
			ReviewDto info = ReviewDto.builder()
						.id(review.getId())
						.user_id(review.getUser().getId())
						.user_nickname(review.getUser().getNickname())
						.user_img(review.getUser().getImg())
						.gosu(review.getUser().isGosu())
						.product_id(review.getProduct().getId())
						.product_name(review.getProduct().getName())
						.content(review.getContent())
						.time(review.getTime())
						.star(review.getStar())
						.like(review.getLike())
						.build();
			info.setImg(new ArrayList<String>());
			ArrayList<ReviewImg> imgs= reviewImgRepository.findByUserIdAndReviewId(review.getUser().getId(), review.getId());
			for(ReviewImg img:imgs) {
				info.getImg().add(img.getImg());
			}
			dto.getHotreview().add(info);
		}
		
		dto.setGosureview(new ArrayList<ReviewDto>());
		ArrayList<Review> gosureview =reviewRepository.findTop3ByUserGosuOrderByIdDesc(true);
		for(Review review: gosureview) {			
			ReviewDto info = ReviewDto.builder()
						.id(review.getId())
						.user_id(review.getUser().getId())
						.user_nickname(review.getUser().getNickname())
						.user_img(review.getUser().getImg())
						.gosu(review.getUser().isGosu())
						.product_id(review.getProduct().getId())
						.product_name(review.getProduct().getName())
						.content(review.getContent())
						.time(review.getTime())
						.star(review.getStar())
						.like(review.getLike())
						.build();
			info.setImg(new ArrayList<String>());
			ArrayList<ReviewImg> imgs= reviewImgRepository.findByUserIdAndReviewId(review.getUser().getId(), review.getId());
			for(ReviewImg img:imgs) {
				info.getImg().add(img.getImg());
			}
			dto.getGosureview().add(info);
		}
		
		dto.setHotproductsreview(new ArrayList<ReviewDto>());
		ArrayList<Product> hotproduct =productRepository.findTop3ByOrderByReviewDesc();
		for(Product product: hotproduct) {
			Optional<Review> reviewTemp=reviewRepository.findFirstByProductIdOrderByIdDesc(product.getId());
			if(!reviewTemp.isPresent())break;
			Review review=reviewTemp.get();
			ReviewDto info= ReviewDto.builder()
					.id(review.getId())
					.user_id(review.getUser().getId())
					.user_nickname(review.getUser().getNickname())
					.user_img(review.getUser().getImg())
					.gosu(review.getUser().isGosu())
					.product_id(review.getProduct().getId())
					.product_name(review.getProduct().getName())
					.content(review.getContent())
					.time(review.getTime())
					.star(review.getStar())
					.like(review.getLike())
					.build();
			info.setImg(new ArrayList<String>());
			ArrayList<ReviewImg> imgs= reviewImgRepository.findByUserIdAndReviewId(review.getUser().getId(), review.getId());
			for(ReviewImg img:imgs) {
				info.getImg().add(img.getImg());
			}
			dto.getHotproductsreview().add(info);
		}
		
	
		dto.setRecentreview(new ArrayList<ReviewDto>());
		ArrayList<Review> recent =reviewRepository.findTop3ByOrderByIdDesc();
		for(Review review: recent) {
			ReviewDto info= ReviewDto.builder()
					.id(review.getId())
					.user_id(review.getUser().getId())
					.user_nickname(review.getUser().getNickname())
					.user_img(review.getUser().getImg())
					.gosu(review.getUser().isGosu())
					.product_id(review.getProduct().getId())
					.product_name(review.getProduct().getName())
					.content(review.getContent())
					.time(review.getTime())
					.star(review.getStar())
					.like(review.getLike())
					.build();
			info.setImg(new ArrayList<String>());
			ArrayList<ReviewImg> imgs= reviewImgRepository.findByUserIdAndReviewId(review.getUser().getId(), review.getId());
			for(ReviewImg img:imgs) {
				info.getImg().add(img.getImg());
			}
			dto.getRecentreview().add(info);
		}
		
		return dto;
	}
	@Transactional
	@Override
	public int createReview(User user, ReviewRequestDto dto) throws Exception {
		Optional<Product> productTemp = productRepository.findById(dto.getProduct_id());
		if(!productTemp.isPresent()) throw new ProductNotFoundException();
		Product product=productTemp.get();
		Review review = Review.builder()
				.user(user)
				.product(product)
				.content(dto.getContent())
				.time(LocalDate.now())
				.star(dto.getStar())
				.like(0)
				.build();
		reviewRepository.save(review);
		for(String st:dto.getImg()) {
		ReviewImg reviewimg=ReviewImg.builder()
				.user(user)
				.img(st)
				.review(review)
				.build();
		reviewImgRepository.save(reviewimg);
		
		}
		
				
		long count =reviewRepository.countByUserId(user.getId());
		user.setReview_count((int)count);
		if(count>10) user.setGosu(true);
		count=reviewRepository.countByProductId(dto.getProduct_id());
		product.setReview((int)count);
		
		userRepository.save(user);
		int reviewid=reviewRepository.findFirstByUserIdOrderByIdDesc(user.getId()).get().getId();
		return reviewid;
		
	}
	
	@Override
	public ReviewDto getReviewDetail(int id) throws Exception {
		Optional<Review> reviewTemp=reviewRepository.findById(id);
		if(!reviewTemp.isPresent()) throw new ReviewNotFoundException();
		Review review=reviewTemp.get();
		ReviewDto dto=ReviewDto.builder()
				.id(id)
				.user_id(review.getUser().getId())
				.user_nickname(review.getUser().getNickname())
				.user_img(review.getUser().getImg())
				.gosu(review.getUser().isGosu())
				.product_id(review.getProduct().getId())
				.product_name(review.getProduct().getName())
				.content(review.getContent())
				.time(review.getTime())
				.star(review.getStar())
				.like(review.getLike())
				.build();
		dto.setImg(new ArrayList<String>());
		ArrayList<ReviewImg> imgs= reviewImgRepository.findByUserIdAndReviewId(review.getUser().getId(), review.getId());
		for(ReviewImg img:imgs) {
			dto.getImg().add(img.getImg());
		}
		return dto;
	}
	
	@Override
	@Transactional
	public void modifyReview(User user, ModifyReviewRequestDto dto) throws Exception {
		Optional<Review> reviewTemp =reviewRepository.findById(dto.getId());
		if(!reviewTemp.isPresent()) throw new ReviewNotFoundException();
		Review review=reviewTemp.get();
		if(review.getUser().getId() != user.getId()) throw new DifferentUserException();
			review.setStar(dto.getStar());
			review.setContent(dto.getContent());
		reviewRepository.save(review);
		ArrayList<ReviewImg> list=reviewImgRepository.findByUserIdAndReviewId(user.getId(), review.getId());
		ArrayList<String> inputimg=dto.getImg();
		ArrayList<String> img=new ArrayList<>();
		for(ReviewImg imgs:list) {
			int num=0;
			for(String dtoimg: inputimg) {
				if(imgs.getImg().equals(dtoimg)) {
					num=1;
					break;
				}
			}
			if(num==0) img.add(imgs.getImg());
		}
		s3upload.deletefile(img);
		reviewImgRepository.deleteByUserIdAndReviewId(user.getId(), review.getId());
		for(String st:dto.getImg()) {
			ReviewImg reviewimg=ReviewImg.builder()
					.user(user)
					.img(st)
					.review(review)
					.build();
			reviewImgRepository.save(reviewimg);
		}
	}
	
	@Override
	@Transactional
	public void deleteReview(User user, int id) throws Exception{
		Optional<Review> reviewTemp= reviewRepository.findById(id);
		if(!reviewTemp.isPresent()) throw new ReviewNotFoundException();
		Review review = reviewTemp.get();
		if(review.getUser().getId() != user.getId()) throw new DifferentUserException();
		ArrayList<ReviewImg> list=reviewImgRepository.findByUserIdAndReviewId(user.getId(), id);
		ArrayList<String> img=new ArrayList<>();
		for(ReviewImg imgs:list) {
			img.add(imgs.getImg());
		}
		s3upload.deletefile(img);
		likeReviewRepository.deleteByReviewIdAndUserId(id,user.getId());
		reviewImgRepository.deleteByUserIdAndReviewId(user.getId(),id);
		reviewRepository.deleteById(id);
		long count =reviewRepository.countByUserId(user.getId());
		user.setReview_count((int)count);
		if(count<=10) user.setGosu(false);
		count=reviewRepository.countByProductId(review.getProduct().getId());
		Product product=productRepository.findById(review.getProduct().getId()).get();
		product.setReview((int)count);
		userRepository.save(user);
	}
	
	@Override
	@Transactional
	public void addLike(int review_id, User user) throws Exception {
		long check =likeReviewRepository.countByReviewIdAndUserId(review_id, user.getId());
		if(check != 0) throw new LikeException();
		Optional<Review> review= reviewRepository.findById(review_id);
		if(!review.isPresent()) throw new ReviewNotFoundException();
		LikeReview likeReview=LikeReview.builder()
					.user(user)
					.review(review.get())
					.build();
		likeReviewRepository.save(likeReview);
		long num=likeReviewRepository.countByReviewId(review_id);
		review.get().setLike((int)num);
		
	}

	@Override
	@Transactional
	public void deleteLike(int review_id, User user) throws Exception {
		long check =likeReviewRepository.countByReviewIdAndUserId(review_id, user.getId());
		if(check == 0) throw new LikeException();
		Optional<Review> review= reviewRepository.findById(review_id);
		if(!review.isPresent()) throw new ReviewNotFoundException();
		likeReviewRepository.deleteByReviewIdAndUserId(review_id, user.getId());
		long num=likeReviewRepository.countByReviewId(review_id);
		review.get().setLike((int)num);
		
	}
	@Override
	public ArrayList<ReviewDto> getmorelistPage(String value) {
		ArrayList<ReviewDto> list= new ArrayList<ReviewDto>();
		if(value.equals("hotreview")) {
			ArrayList<Review> hotreview = reviewRepository.findTop10ByTimeBetweenOrderByLikeDesc(LocalDate.now().minusDays(2),LocalDate.now());
			for(Review review: hotreview) {			
				ReviewDto info = ReviewDto.builder()
							.id(review.getId())
							.user_id(review.getUser().getId())
							.user_nickname(review.getUser().getNickname())
							.user_img(review.getUser().getImg())
							.gosu(review.getUser().isGosu())
							.product_id(review.getProduct().getId())
							.product_name(review.getProduct().getName())
							.content(review.getContent())
							.time(review.getTime())
							.star(review.getStar())
							.like(review.getLike())
							.build();
				info.setImg(new ArrayList<String>());
				ArrayList<ReviewImg> imgs= reviewImgRepository.findByUserIdAndReviewId(review.getUser().getId(), review.getId());
				for(ReviewImg img:imgs) {
					info.getImg().add(img.getImg());
				}
				list.add(info);
			}
		}else if(value.equals("gosureview")) {
			ArrayList<Review> gosureview =reviewRepository.findTop10ByUserGosuOrderByIdDesc(true);
			for(Review review: gosureview) {			
				ReviewDto info = ReviewDto.builder()
							.id(review.getId())
							.user_id(review.getUser().getId())
							.user_nickname(review.getUser().getNickname())
							.user_img(review.getUser().getImg())
							.gosu(review.getUser().isGosu())
							.product_id(review.getProduct().getId())
							.product_name(review.getProduct().getName())
							.content(review.getContent())
							.time(review.getTime())
							.star(review.getStar())
							.like(review.getLike())
							.build();
				info.setImg(new ArrayList<String>());
				ArrayList<ReviewImg> imgs= reviewImgRepository.findByUserIdAndReviewId(review.getUser().getId(), review.getId());
				for(ReviewImg img:imgs) {
					info.getImg().add(img.getImg());
				}
				list.add(info);
			}
		}else if(value.equals("recentreview")){
			ArrayList<Review> recent =reviewRepository.findTop10ByOrderByIdDesc();
			for(Review review: recent) {
				ReviewDto info= ReviewDto.builder()
						.id(review.getId())
						.user_id(review.getUser().getId())
						.user_nickname(review.getUser().getNickname())
						.user_img(review.getUser().getImg())
						.gosu(review.getUser().isGosu())
						.product_id(review.getProduct().getId())
						.product_name(review.getProduct().getName())
						.content(review.getContent())
						.time(review.getTime())
						.star(review.getStar())
						.like(review.getLike())
						.build();
				info.setImg(new ArrayList<String>());
				ArrayList<ReviewImg> imgs= reviewImgRepository.findByUserIdAndReviewId(review.getUser().getId(), review.getId());
				for(ReviewImg img:imgs) {
					info.getImg().add(img.getImg());
				}
				list.add(info);
			}
		}else if(value.equals("Hotproductsreview")){
			ArrayList<Product> hotproduct =productRepository.findTop10ByOrderByReviewDesc();
			for(Product product: hotproduct) {
				Optional<Review> reviewTemp=reviewRepository.findFirstByProductIdOrderByIdDesc(product.getId());
				if(!reviewTemp.isPresent())break;
				Review review=reviewTemp.get();
				ReviewDto info= ReviewDto.builder()
						.id(review.getId())
						.user_id(review.getUser().getId())
						.user_nickname(review.getUser().getNickname())
						.user_img(review.getUser().getImg())
						.gosu(review.getUser().isGosu())
						.product_id(review.getProduct().getId())
						.product_name(review.getProduct().getName())
						.content(review.getContent())
						.time(review.getTime())
						.star(review.getStar())
						.like(review.getLike())
						.build();
				info.setImg(new ArrayList<String>());
				ArrayList<ReviewImg> imgs= reviewImgRepository.findByUserIdAndReviewId(review.getUser().getId(), review.getId());
				for(ReviewImg img:imgs) {
					info.getImg().add(img.getImg());
				}
				list.add(info);
			}
		}else {
			return null;
		}
		return list;
	}
	
	@Override
	public boolean getlikeCheck(int review_id,User user) throws Exception {
		if(!reviewRepository.findById(review_id).isPresent()) throw new ReviewNotFoundException();
		long num=likeReviewRepository.countByReviewIdAndUserId(review_id, user.getId());
		if(num==1)return true;
		else if(num==0) return false;
		else throw new LikeException();
	}

}
