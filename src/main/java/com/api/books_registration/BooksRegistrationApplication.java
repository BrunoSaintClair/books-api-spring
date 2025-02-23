package com.api.books_registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BooksRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksRegistrationApplication.class, args);
	}

	@GetMapping("/hello-world")
	public String helloWorld(){
		return "Hello world!";
	}
}
