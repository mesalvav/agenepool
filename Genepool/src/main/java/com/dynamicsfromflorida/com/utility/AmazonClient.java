package com.dynamicsfromflorida.com.utility;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AmazonClient {

    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    public String getEndpointUrl() {
		return endpointUrl;
	}

	@Value("${amazonProperties.bucketName}")
    private String bucketName;
    
    public String getBucketName() {
   
		return bucketName;
	}

	@Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    
    @PostConstruct
    private void initializeAmazon() {
       // AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
       // this.s3client = new AmazonS3Client(credentials);
       
       BasicAWSCredentials creds = new BasicAWSCredentials(this.accessKey, this.secretKey);
       this.s3client = 
    		   AmazonS3ClientBuilder.standard().withRegion("us-east-2")
    		   .withCredentials(new AWSStaticCredentialsProvider(creds)).build();
    }

	public void putObject(String fileName, File file) {
		this.s3client.putObject(new PutObjectRequest(bucketName, fileName, file));
	           // .withCannedAcl(CannedAccessControlList.PublicRead));
	}
}
