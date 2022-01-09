package com.dynamicsfromflorida.com.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class FileToFromAWS {
	
	@Autowired
	private AmazonClient s3client;
	
	public String saveFile(String uploadDir, MultipartFile multipartFile) throws IOException {
        
		String fileUrl = "";
		String generatedfileName = "";
		String resultFileURL = "";
	    try {
	    	File file = convertMultiPartToFile(multipartFile);
	    	
	        generatedfileName = generateFileName(multipartFile);
	        String fileName = "inputgenecode/" + generatedfileName;
	        fileUrl = s3client.getEndpointUrl() + "/" + s3client.getBucketName() + "/" + fileName;
	        
	        resultFileURL = 
	        		s3client.getEndpointUrl() + "/" + s3client.getBucketName() + "/outputgenecode/" + generatedfileName;
	        
	        uploadFileTos3bucket(fileName, file);
	        file.delete();
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
	    // TODO: add array or tupled [urlfile, generatedFileName]
	    return resultFileURL;
    }
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
	
	private String generateFileName(MultipartFile multiPart) {
	    return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}
	
	private void uploadFileTos3bucket(String fileName, File file) {
	    s3client.putObject(fileName, file);
	            
	}
}
