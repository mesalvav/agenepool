package com.dynamicsfromflorida.com.utility;


import java.io.*;
import java.nio.file.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.dynamicsfromflorida.com.controllers.FileuploadController;

public class FileUploadUtil {
	
	
	
	public static void saveFile(String uploadDir, String fileName,
            MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
         
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            
            
	        
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }   
        
    }
}
