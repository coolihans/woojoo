package com.wooju.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3uploadImpl implements S3upload{
	
	 @Value("${cloud.aws.s3.bucket}")
	 private String bucket;
	 
	 @Value("${cloud.aws.region.static}")
	 private String region;
	 
	@Autowired
	AmazonS3 amazonS3;

	@Override
	public String upload(MultipartFile multipartFile) throws IOException {
		 String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

	        ObjectMetadata objMeta = new ObjectMetadata();
	        objMeta.setContentLength(multipartFile.getInputStream().available());

	        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);
	        return amazonS3.getUrl(bucket, s3FileName).toString();
	}

	@Override
	public ArrayList<String> uploads(ArrayList<MultipartFile> file) throws IOException {
		
		ArrayList<String> fileNameList = new ArrayList<>();
		for(MultipartFile f:file) {
			
		String s3FileName = UUID.randomUUID() + "-" + f.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(f.getInputStream().available());

        amazonS3.putObject(bucket, s3FileName, f.getInputStream(), objMeta);
		fileNameList.add(amazonS3.getUrl(bucket, s3FileName).toString());
		}
        return fileNameList;

	}

	@Override
	public void deletefile(ArrayList<String> file) throws IOException {
		for(String img:file) {
			String st[]=img.split("/");
			if(st.length>=4) amazonS3.deleteObject(bucket, st[3]);
		}
	}
	
}
