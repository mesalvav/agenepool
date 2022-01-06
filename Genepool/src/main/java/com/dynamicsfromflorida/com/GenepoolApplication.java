package com.dynamicsfromflorida.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class GenepoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenepoolApplication.class, args);
	}

	@RequestMapping("/")
    String helloWorld() {
        return "Hello World! from gene pool";
    }
}
