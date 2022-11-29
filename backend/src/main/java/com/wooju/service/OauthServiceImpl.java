package com.wooju.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


@Service
@PropertySource("classpath:application-oauth.properties")
public class OauthServiceImpl implements OauthService{
	
	@Value("${google.client.id}")
    private String googleClientId;
	
	@Value("${google.client.secret}")
    private String googleClientSecret;
	
	@Value("${google.redirect.uri}")
    private String googleRedirectUri;	
	
	@Value("${naver.client.id}")
    private String naverClientId;
	
	@Value("${naver.client.secret}")
    private String naverClientSecret;
	
	@Override
	public String getGoogleAccessToken(String code) {
		String access_Token="";
        String reqURL = "https://oauth2.googleapis.com/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=").append(googleClientId); // TODO REST_API_KEY 입력
            sb.append("&client_secret=").append(googleClientSecret);
            sb.append("&redirect_uri=").append(googleRedirectUri);
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();
            
            
            int responseCode = conn.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
	}
	
	@Override
	public String createGoogleUser(String token) {

        String reqURL = "https://oauth2.googleapis.com/tokeninfo";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("access_token=").append(token);
            bw.write(sb.toString());
            bw.flush();
            int responseCode = conn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            String email = element.getAsJsonObject().get("email").getAsString();
            br.close();
            return email;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

	@Override
	public String getNaverAccessToken(String code) {
		String access_Token="";
        String reqURL = "https://nid.naver.com/oauth2.0/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=").append(naverClientId); // TODO REST_API_KEY 입력
            sb.append("&client_secret=").append(naverClientSecret);
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();
            
            
            int responseCode = conn.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result); 
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
           
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
	}

	@Override
	public String createNaverUser(String token) {

        String reqURL = "https://openapi.naver.com/v1/nid/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            int responseCode = conn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            String email = element.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();
            br.close();
            return email;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
