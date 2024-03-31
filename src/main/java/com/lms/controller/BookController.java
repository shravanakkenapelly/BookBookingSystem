package com.lms.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.lms.dto.AddBookDTO;
import com.lms.dto.BookDTO;
import com.lms.dto.BorrowBook;
import com.lms.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService service;

	@PostMapping("/addbookbylibraryid/{id}")
	public ResponseEntity<String> addBook(@PathVariable("id") Long id,@RequestBody BookDTO bookDTO){
		return service.addBook(id,bookDTO);
	}
	@GetMapping("/getallbooks")
    public ResponseEntity<List<BookDTO>> getallBooks(){
		return service.getallBooks();
	}
	@PutMapping("/updatebookbybookid/{id}")
	public ResponseEntity<String> updateBook(@PathVariable("id") Long id,@RequestBody BookDTO bookDTO){
		return service.updateBook(id,bookDTO);
	}
	@DeleteMapping("/deletebookbyid/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") Long id){
		return service.deleteBook(id);
	}

	@PostMapping("/borrow")
	public ResponseEntity<BookDTO> borrowBook(@RequestParam(name = "customer_email") String customer_email, @RequestParam(name = "book_name") String book_name) {
	    return service.borrowBook(customer_email, book_name);
	}
	
	@PostMapping("/return")
	public ResponseEntity<BookDTO> returnBook(@RequestParam(name="customer_email") String customer_email,@RequestParam(name="book_name") String book_name){
		return service.returnBook(customer_email,book_name);
		
	}

}
