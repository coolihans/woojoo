package com.wooju.service;


import com.wooju.dto.ProfileDto;
import com.wooju.dto.SurveyDto;
import com.wooju.dto.request.ModifyProfileRequestDto;
import com.wooju.dto.request.SignUpRequestDto;
import com.wooju.dto.request.SurveyRequestDto;
import com.wooju.entity.User;



public interface UserService {

	User getUserByEmail(String email, String usertype) throws Exception;
	void SignupUser(SignUpRequestDto signUpInfo)throws Exception;
	User getUserById(int id) throws Exception;
	ProfileDto getProfile(int id) throws Exception;
	void modifyProfile(ModifyProfileRequestDto dto, int id) throws Exception;
	SurveyDto getSurvey(User user);
	Object getrecom(User user,SurveyRequestDto dto);
	void modifyrecom(User user, SurveyRequestDto dto);

}
