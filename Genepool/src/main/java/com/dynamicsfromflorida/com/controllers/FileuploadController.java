package com.dynamicsfromflorida.com.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.dynamicsfromflorida.com.entity.Textfile;
import com.dynamicsfromflorida.com.repository.TextfileRepository;
import com.dynamicsfromflorida.com.utility.FileToFromAWS;
import com.dynamicsfromflorida.com.utility.FileUploadUtil;
import com.dynamicsfromflorida.com.entity.Textfile;

@Controller
public class FileuploadController {
	
	 	@Autowired
	    private TextfileRepository repo;
	 	
	 	@Autowired
	 	private FileToFromAWS fileToFromAWS;
	
	 Logger logger = LoggerFactory.getLogger(FileuploadController.class);

		@RequestMapping("/textfile")
	    public String totextfile(Model model) {
			
			Textfile newfilex = new Textfile("defaultname");
			model.addAttribute("newfilex", newfilex);
			
			List<Textfile>  textfiles = repo.findAll();
			model.addAttribute("textfiles", textfiles);
			
	        return "textfile/textfile";
	    }
	
	
	    @PostMapping("/textfile/save")
	    public RedirectView saveUser(Textfile textfile,
	            @RequestParam("file") MultipartFile multipartFile) throws IOException {
	         
	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        
	        Random ran = new Random();
	        Integer morethan100 = 101 + ran.nextInt(897);
	        // fileName =  morethan100 + "_" + fileName;
	        fileName = StringUtils.cleanPath(fileName);
	        
	        logger.warn("This is a WARN message.");
	        logger.error("your filenme is  " + fileName);
	        logger.warn("This is a WARN message.");
	        
	        
	        String uploadDir = "Archivos";
	        
	        String urlOutputFileName = fileToFromAWS.saveFile(uploadDir, multipartFile);
	        
	        textfile.setFilename(fileName);
	        textfile.setUrlOutputFileName(urlOutputFileName);
	        
	        Textfile savedTextFile = repo.save(textfile);
	         
	        return new RedirectView("/", true);
	    }
}
