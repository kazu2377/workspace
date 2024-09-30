package com.juniordevmind.bookapi.book_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.juniordevmind.shared.errors.RestResponseEntityExceptionHandler;

@SpringBootApplication
@Import({ RestResponseEntityExceptionHandler.class })
public class BookApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApiApplication.class, args);
	}

}
