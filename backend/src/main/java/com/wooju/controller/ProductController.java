package com.wooju.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wooju.auth.UserInfo;
import com.wooju.dto.ProductReviewDto;
import com.wooju.dto.request.ProductLikeRequestDto;
import com.wooju.dto.request.ProductListRequestDto;
import com.wooju.dto.response.BaseResponseDto;
import com.wooju.dto.response.LikeCheckResponseDto;
import com.wooju.dto.response.ProductDetailResponseDto;
import com.wooju.dto.response.ProductListResponseDto;
import com.wooju.entity.User;
import com.wooju.service.ProductService;
import com.wooju.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "술API", tags = {"Product"})
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@PostMapping("")
	@ApiOperation(value="술 리스트 조회", notes ="술 리스트 가져오기")
	@ApiResponses({
		@ApiResponse(code = 200 , message="성공"),
		@ApiResponse(code = 500 , message="서버오류")
	})
	public ResponseEntity<? extends BaseResponseDto> productlist(@RequestBody @ApiParam(value="리스트 정보", required=true) ProductListRequestDto dto) {
		 Object result=productService.getlist(dto);
		return ResponseEntity.status(200).body(ProductListResponseDto.of(200, "Success",result));
	}
	
	@GetMapping("/{product_id}")
	@ApiOperation(value="술 디테일 조회", notes ="술 상세정보 가져오기")
	@ApiResponses({
		@ApiResponse(code = 200 , message="성공"),
		@ApiResponse(code = 500 , message="서버오류")
	})
	public ResponseEntity<? extends BaseResponseDto> productdetail(@PathVariable("product_id") int id){
		 Object result=productService.getdetail(id);
		 ArrayList<ProductReviewDto> list=productService.getReviewList(id);
		return ResponseEntity.status(200).body(ProductDetailResponseDto.of(200, "Success",result,list));
	}
	
	@GetMapping("/likeCheck/{product_id}")
	@ApiOperation(value="술 좋아요 체크", notes ="술좋아요 여부 확인하기")
	@ApiResponses({
		@ApiResponse(code = 200 , message="성공"),
		@ApiResponse(code = 401 , message="부적절한 토큰"),
		@ApiResponse(code = 500 , message="서버오류")
	})
	public ResponseEntity<? extends BaseResponseDto> productlikeCheck(@ApiIgnore Authentication authentication,
			@PathVariable("product_id") int id) throws Exception{
		
		if(authentication == null) return ResponseEntity.status(401).body(BaseResponseDto.of(401,"failed"));
		UserInfo userDetails=(UserInfo) authentication.getDetails();
		User user=userDetails.getUser();
		
		boolean check=productService.getlikeCheck(id,user);
		

		return ResponseEntity.status(200).body(LikeCheckResponseDto.of(200, "Success",check));
	}
	
	
	@PostMapping("/like")
	@ApiOperation(value="좋아요", notes ="술 좋아요")
	@ApiResponses({
		@ApiResponse(code = 200 , message="성공"),
		@ApiResponse(code = 401 , message="부적절한 토큰"),
		@ApiResponse(code = 500 , message="서버오류")
	})
	public ResponseEntity<BaseResponseDto> addlike(@RequestBody @ApiParam(value="술 정보", required=true) ProductLikeRequestDto productlike,
			@ApiIgnore Authentication authentication) throws Exception{
		if(authentication == null) return ResponseEntity.status(401).body(BaseResponseDto.of(401,"failed"));
		UserInfo userDetails=(UserInfo) authentication.getDetails();
		User user=userDetails.getUser();
		productService.addLike(productlike.getProduct_id(),user);
		return ResponseEntity.status(200).body(BaseResponseDto.of(200, "Success"));
	}
	
	@DeleteMapping("/like/{product_id}")
	@ApiOperation(value="좋아요 취소", notes ="술 좋아요 취소")
	@ApiResponses({
		@ApiResponse(code = 200 , message="성공"),
		@ApiResponse(code = 401 , message="부적절한 토큰"),
		@ApiResponse(code = 500 , message="서버오류")
	})
	public ResponseEntity<BaseResponseDto> deletelike(@PathVariable("product_id") int id,
			@ApiIgnore Authentication authentication) throws Exception{
		if(authentication == null) return ResponseEntity.status(401).body(BaseResponseDto.of(401,"failed"));
		UserInfo userDetails=(UserInfo) authentication.getDetails();
		User user=userDetails.getUser();
		productService.deleteLike(id,user);
		return ResponseEntity.status(200).body(BaseResponseDto.of(200, "Success"));
	}
	
}
