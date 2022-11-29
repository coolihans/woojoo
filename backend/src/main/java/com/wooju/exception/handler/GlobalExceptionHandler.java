package com.wooju.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wooju.dto.response.BaseResponseDto;
import com.wooju.exception.DifferentUserException;
import com.wooju.exception.ExistUserException;
import com.wooju.exception.LikeException;
import com.wooju.exception.ProductNotFoundException;
import com.wooju.exception.ReviewNotFoundException;
import com.wooju.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<BaseResponseDto> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(403).body(BaseResponseDto.of(403, "User Not Found"));
    }

	@ExceptionHandler(ReviewNotFoundException.class)
    protected ResponseEntity<BaseResponseDto> handleReviewNotFoundException(ReviewNotFoundException e) {
        return ResponseEntity.status(402).body(BaseResponseDto.of(402, "Review Not Found"));
    }
	
	@ExceptionHandler(ProductNotFoundException.class)
    protected ResponseEntity<BaseResponseDto> handleProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(406).body(BaseResponseDto.of(406, "Product Not Found"));
    }
	
	@ExceptionHandler(LikeException.class)
    protected ResponseEntity<BaseResponseDto> handleLikeException(LikeException e) {
        return ResponseEntity.status(405).body(BaseResponseDto.of(405, "Like Error"));
    }
	
	@ExceptionHandler(ExistUserException.class)
    protected ResponseEntity<BaseResponseDto> handleExistUserException(ExistUserException e) {
        return ResponseEntity.status(407).body(BaseResponseDto.of(407, "User Exist"));
    }
	
	@ExceptionHandler(DifferentUserException.class)
    protected ResponseEntity<BaseResponseDto> handleDifferentUserException(DifferentUserException e) {
        return ResponseEntity.status(408).body(BaseResponseDto.of(408, "User Different"));
    }
}
