package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return Arrays.asList(
				new Book(1l,"Mathematics","RangaSwamy"),
				new Book(2l,"Physics","Ray sean sebastian"),
				new Book(3l,"Chemistry","Makkar"));
	}
}
