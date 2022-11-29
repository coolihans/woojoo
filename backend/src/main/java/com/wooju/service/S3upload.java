package com.wooju.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public interface S3upload {
	public String upload(MultipartFile multipartFile) throws IOException;
	public void deletefile(ArrayList<String> file) throws IOException;	
	public ArrayList<String> uploads(ArrayList<MultipartFile> file) throws IOException;
	
}
