package com.juniordevmind.authorapi.author_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.juniordevmind.shared.errors.RestResponseEntityExceptionHandler;

@SpringBootApplication
@Import({ RestResponseEntityExceptionHandler.class })
public class AuthorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorApiApplication.class, args);
	}

}
