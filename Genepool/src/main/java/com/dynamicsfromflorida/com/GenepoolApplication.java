package com.dynamicsfromflorida.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dynamicsfromflorida.com.repository.StudentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;



@SpringBootApplication
public class GenepoolApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(GenepoolApplication.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		studentRepository.saveOneStudent();
		//repository.playWithEntityManager();
	}
	
}
