package com.wooju.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wooju.dto.response.BaseResponseDto;
import com.wooju.dto.response.ImageListResponseDto;
import com.wooju.dto.response.ImageResponseDto;
import com.wooju.service.S3upload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "이미지 API", tags = {"Image"})
@RestController
@RequestMapping("/image")
public class ImageController {

	@Autowired
	S3upload s3Upload;
	
	@PostMapping("/upload")
	@ApiOperation(value="이미지 등록", notes ="새 이미지 업로드")
	@ApiResponses({
		@ApiResponse(code = 200 , message="성공"),
		@ApiResponse(code = 500 , message="서버오류")
	})
	public ResponseEntity<? extends BaseResponseDto> upload (@RequestPart MultipartFile file) throws IOException{
		String img=s3Upload.upload(file);
		
		return ResponseEntity.status(200).body(ImageResponseDto.of(200,"success", img));
	}
	
	@PostMapping("/uploads")
	@ApiOperation(value="이미지 등록", notes ="새 이미지 업로드")
	@ApiResponses({
		@ApiResponse(code = 200 , message="성공"),
		@ApiResponse(code = 500 , message="서버오류")
	})
	public ResponseEntity<? extends BaseResponseDto> uploads (@RequestPart ArrayList<MultipartFile> file) throws IOException{
		ArrayList<String> imgs=s3Upload.uploads(file);
		
		return ResponseEntity.status(200).body(ImageListResponseDto.of(200,"success", imgs));
	}
	
}
