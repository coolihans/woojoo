package com.wooju.service;

public interface OauthService {
	public String getGoogleAccessToken(String code);
	String createGoogleUser(String token);
	public String getNaverAccessToken(String code);
	public String createNaverUser(String accessToken);
}
