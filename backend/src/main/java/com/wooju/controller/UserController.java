package com.wooju.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wooju.auth.UserInfo;
import com.wooju.dto.ProfileDto;
import com.wooju.dto.SurveyDto;
import com.wooju.dto.request.ModifyProfileRequestDto;
import com.wooju.dto.request.SignUpRequestDto;
import com.wooju.dto.request.SurveyRequestDto;
import com.wooju.dto.response.BaseResponseDto;
import com.wooju.dto.response.FirstLoginResponseDto;
import com.wooju.dto.response.LoginResponseDto;
import com.wooju.dto.response.ProfileResponseDto;
import com.wooju.dto.response.RecomResponseDto;
import com.wooju.dto.response.SurveyResponseDto;
import com.wooju.entity.User;
import com.wooju.service.OauthService;
import com.wooju.service.S3upload;
import com.wooju.service.UserService;
import com.wooju.util.JwtTokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	OauthService oauthService;
	
	@Autowired
	S3upload s3upload;

	@GetMapping("/login-google")
    public ResponseEntity<? extends BaseResponseDto> googleCallback(@RequestParam String code) throws IOException {
       String accessToken = oauthService.getGoogleAccessToken(code);
       String email = oauthService.createGoogleUser(accessToken);
       try {
    	   User user = userService.getUserByEmail(email,"google");
    	   return ResponseEntity.status(200).body(LoginResponseDto.of(200, "Success", JwtTokenUtil.getToken(user)));
       } catch (Exception e) {
    	   return ResponseEntity.status(201).body(FirstLoginResponseDto.of(201, "Signup Required", email, "google"));
       }
    }
	
	@GetMapping("/login-naver")
    public ResponseEntity<? extends BaseResponseDto> naverCallback(@RequestParam String code) throws IOException {
       String accessToken = oauthService.getNaverAccessToken(code);
       String email = oauthService.createNaverUser(accessToken);
       try {
    	   User user = userService.getUserByEmail(email,"naver");
    	   return ResponseEntity.status(200).body(LoginResponseDto.of(200, "Success", JwtTokenUtil.getToken(user)));
       } catch (Exception e) {
    	   return ResponseEntity.status(201).body(FirstLoginResponseDto.of(201, "Signup Required", email, "naver"));
       }
    }
	
	@PostMapping("/signup")
	@ApiOperation(value="회원 가입", notes ="신규 유저 회원 가입")
	@ApiResponses({
		@ApiResponse(code = 200 , message="성공"),
		@ApiResponse(code = 500 , message="서버오류")
	})
	public ResponseEntity<BaseResponseDto> SignUp(@RequestBody  @ApiParam(value="회원 정보", required=true)SignUpRequestDto signUpInfo) throws Exception{
		userService.SignupUser(signUpInfo);
		return ResponseEntity.status(200).body(BaseResponseDto.of(200, "Success"));
	}
	
	@GetMapping("/profile")
	@ApiOperation(value="회원 정보", notes ="내 정보 확인")
	@ApiResponses({
		@ApiResponse(code = 200 , message="성공"),
		@ApiResponse(code = 401 , message="부적절한 토큰"),
		@ApiResponse(code = 500 , message="서버오류")
	})
	public ResponseEntity<? extends BaseResponseDto> myprofile(@ApiIgnore Authentication authentication) throws Exception{
		if(authentication == null) return ResponseEntity.status(401).body(BaseResponseDto.of(401,"failed"));
		UserInfo userDetails=(UserInfo) authentication.getDetails();
		int id=userDetails.getUserId();
		
		ProfileDto dto = userService.getProfile(id); 
		
		
		return ResponseEntity.status(200).body(ProfileResponseDto.of(200, "Success",dto));
	}
	
	@PutMapping("/profile")
	@ApiOperation(value="회원 정보",notes="내 정보 수정")
	@ApiResponses({
		@ApiResponse(code = 200 , message="성공"),
		@ApiResponse(code = 401 , message="부적절한 토큰"),
		@ApiResponse(code = 500 , message="서버오류")
	})
	public ResponseEntity<? extends BaseResponseDto> modifyprofile(@ApiIgnore Authentication authentication 
			, @RequestBody @ApiParam(value="프로필", required=true)ModifyProfileRequestDto dto) throws Exception{
		if(authentication == null) return ResponseEntity.status(401).body(BaseResponseDto.of(401,"failed"));
		UserInfo userDetails=(UserInfo) authentication.getDetails();
		int id=userDetails.getUserId();
		
		userService.modifyProfile(dto,id);
		return ResponseEntity.status(200).body(BaseResponseDto.of(200, "success"));
	}
	
	@GetMapping("/survey")
	@ApiOperation(value="설문 조사",notes="기존 설문 조사 정보 받기")
	@ApiResponses({
		@ApiResponse(code = 200 , message="성공"),
		@ApiResponse(code = 500 , message="서버오류")
	})
	public ResponseEntity<? extends BaseResponseDto>entersurvey(@ApiIgnore Authentication authentication){
		User user;
		if(authentication == null) {
			user=null;
		}
		else {
		UserInfo userDetails=(UserInfo) authentication.getDetails();
			user=userDetails.getUser();
		}
		
		SurveyDto dto=userService.getSurvey(user);
		return ResponseEntity.status(200).body(SurveyResponseDto.of(200, "success",dto));
	}
	
	@PutMapping("/survey")
	@ApiOperation(value="설문 조사 정보 수정",notes="새로운 설문 조사 받아 저장")
	@ApiResponses({
		@ApiResponse(code = 200 , message="성공"),
		@ApiResponse(code = 500 , message="서버오류")
	})
	public ResponseEntity<? extends BaseResponseDto> survey(@ApiIgnore Authentication authentication,
			@RequestBody @ApiParam(value="설문 결과", required=true) SurveyRequestDto dto){
		User user;
		if(authentication == null) {
			user=null;
		}
		else {
			UserInfo userDetails=(UserInfo) authentication.getDetails();
			user=userDetails.getUser();
		}
		userService.modifyrecom(user,dto);
		Object obj=userService.getrecom(user, dto);
		
		return ResponseEntity.status(200).body(RecomResponseDto.of(200, "success",obj));
		
	}
	
	@PostMapping("/survey")
	@ApiOperation(value="기존에 있는 설문 조사 결과 받기",notes="기존에 있는 설문 정보 토대로 결과값 반환")
	@ApiResponses({
		@ApiResponse(code = 200 , message="성공"),
		@ApiResponse(code = 500 , message="서버오류")
	})
	public ResponseEntity<? extends BaseResponseDto> surveyproductlist(@ApiIgnore Authentication authentication,
			@RequestBody @ApiParam(value="설문 결과", required=true) SurveyRequestDto dto){
		User user;
		if(authentication == null)user=null;
		else {
			UserInfo userDetails=(UserInfo) authentication.getDetails();
			user=userDetails.getUser();
		}
		
		Object obj=userService.getrecom(user, dto);
		
		return ResponseEntity.status(200).body(RecomResponseDto.of(200, "success",obj));
		
	}
	
}
