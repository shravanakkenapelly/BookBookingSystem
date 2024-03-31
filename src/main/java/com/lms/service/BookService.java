package com.lms.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import com.lms.dto.AddBookDTO;
import com.lms.dto.BookDTO;
import com.lms.dto.BorrowBook;
import com.lms.dto.CustomerDTO;
import com.lms.entity.Book;
import com.lms.entity.Customer;
import com.lms.entity.Library;
import com.lms.exception.BookNotFound;
import com.lms.exception.LibraryNotFound;
//import com.lms.intf.BookServiceIntf;
import com.lms.repository.BookRepository;
import com.lms.repository.CustomerRepository;
import com.lms.repository.LibraryRepository;

@Service
public class BookService{
	
	@Autowired
	BookRepository repo;
	
	@Autowired
	CustomerRepository customer_repo;
	
	@Autowired
	LibraryRepository libraryRepository;

	
public ResponseEntity<String> addBook(Long id, BookDTO bookDTO) {
		
	    Library libraryid=libraryRepository.findById(id).orElseThrow(
	    		()->new LibraryNotFound(String.format("Library id %d is not found", id))
	    		);
	    		
		
		Book book=new Book();
		book.setName(bookDTO.getBookname());
		book.setAuthor(bookDTO.getAuthor());
		book.setCategory(bookDTO.getCategory());
		book.setStatus(bookDTO.getStatus());
		book.setStock(bookDTO.getStock());
		book.setLibrary(libraryid);
	    repo.save(book);
		return new ResponseEntity<String>("Successfully Added Book",HttpStatus.CREATED);
	}

	public ResponseEntity<List<BookDTO>> getallBooks() {
		List<Book> books=repo.findAll();
		List<BookDTO> booksdto=new ArrayList();
		
		for(Book book:books) {
			BookDTO bookdto=new BookDTO();
			bookdto.setBookname(book.getName());
			bookdto.setAuthor(book.getAuthor());
			bookdto.setCategory(book.getCategory());
			bookdto.setStatus(book.getStatus());
			bookdto.setStock(book.getStock());
			booksdto.add(bookdto);
		}
		return new ResponseEntity<List<BookDTO>>(booksdto,HttpStatus.OK);
	}

	public ResponseEntity<String> updateBook(Long id, BookDTO bookDTO) {
	Book bookid=repo.findById(id).orElseThrow(
			()->new BookNotFound(String.format("Book id %d is Not Found", id)));
	
	bookid.setName(bookDTO.getBookname());
	bookid.setAuthor(bookDTO.getAuthor());
	bookid.setCategory(bookDTO.getCategory());
	bookid.setStatus(bookDTO.getStatus());
	bookid.setStock(bookDTO.getStock());
	repo.save(bookid);
		return new ResponseEntity<String>("Updated Successfully",HttpStatus.OK);
	}

	public ResponseEntity<String> deleteBook(Long id) {
		Book bookid=repo.findById(id).orElseThrow(
				()->new BookNotFound(String.format("Book id %d is not Found", id))
				);
		repo.delete(bookid);
		return new ResponseEntity<String>("Successfully Deleted",HttpStatus.OK);
	}

	
	public ResponseEntity<BookDTO> borrowBook(String customer_email, String book_name) {
	    Customer c = customer_repo.findByEmail(customer_email);
	    if (c == null) {
	        return ResponseEntity.notFound().build();
	    }
	   
	    
	    Book b = repo.findByName(book_name);
	    if (b == null) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    List<Customer> customers=b.getCustomer();
//	    List<CustomerDTO> customerdto=new ArrayList<>();
//	    for(Customer customer1:customer) {
//	    	Customer customerdto1=new Customer();
//	    	customerdto1.setAddress(customer1.getAddress());
//            customerdto1.setEmail(customer1.getEmail());
//            customerdto1.setName(customer1.getName());
//            customerdto1.setPhoneNumber(customer1.getPhoneNumber());
//           customerdto.add(customerdto1);
//	    }
//	
//	    for(Customer customer1:customer) {
//	    	Customer customerdto1=new Customer();
//	    	customerdto1.setAddress(customer1.getAddress());
//            customerdto1.setEmail(customer1.getEmail());
//            customerdto1.setName(customer1.getName());
//            customerdto1.setPhoneNumber(customer1.getPhoneNumber());
//            customer_repo.save(customerdto1);
//	    }
//	    
	   customers.add(c);
	    
	    if (b.getStock() > 0) {
	        b.setStock(b.getStock() - 1);
	      //  b.setCustomer(customer);
	        b.setCustomer(customers);
	        Book updatedBook = repo.save(b);
	        BookDTO bookDTO = bookentityToDTO(updatedBook);
	        return ResponseEntity.ok(bookDTO);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	}


	private BookDTO bookentityToDTO(Book bookdetails) {
		BookDTO bookDTO=new BookDTO();
		bookDTO.setBookname(bookdetails.getName());
		bookDTO.setStock(bookdetails.getStock());
		bookDTO.setAuthor(bookdetails.getAuthor());
		bookDTO.setCategory(bookdetails.getCategory());
		bookDTO.setStatus(bookdetails.getStatus());
	    
		List<Customer> customer=bookdetails.getCustomer();
		List<CustomerDTO> customerdto=new ArrayList<>();
		for(Customer customer1:customer) {
			CustomerDTO customerdto1=new CustomerDTO();
			customerdto1.setAddress(customer1.getAddress());
			customerdto1.setEmail(customer1.getEmail());
			customerdto1.setName(customer1.getName());
			customerdto1.setPhoneNumber(customer1.getPhoneNumber());
			customerdto.add(customerdto1);
		}
		
		if(bookdetails!=null) {
			//bookDTO.setCustomer(CustomerDTO.toDTO(bookdetails.getCustomer()));
		//	bookDTO.setCustomer(CustomerDTO.customerEntityToDTO(bookdetails.getCustomer()));
		bookDTO.setCustomer(customerdto);	
		}
		return bookDTO;
	}

	public ResponseEntity<BookDTO> returnBook(String customer_email, String book_name) {
		Customer c=customer_repo.findByEmail(customer_email);
		if(c==null) {
			return ResponseEntity.notFound().build();
		}
		Book book=repo.findByName(book_name);
		if(book==null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Customer> customers=book.getCustomer();
		
		if(customers.contains(c)) {
		
		customers.remove(c);
		if(book.getStock()>=0) {
			book.setStock(book.getStock()+1);
			book.setCustomer(customers);
			repo.save(book);
			BookDTO bookDTO = bookentityToDTO(book);
		        return ResponseEntity.ok(bookDTO);
		    } else {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		    }
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	
	}
		
	private CustomerDTO customerEntityToDTO(Customer c) {
		CustomerDTO customerdto=new CustomerDTO();
		customerdto.setName(c.getName());
		customerdto.setEmail(c.getEmail());
		customerdto.setPhoneNumber(c.getPhoneNumber());
		customerdto.setAddress(c.getAddress());
		List<Book> books=c.getBooks(); 
		List<BookDTO> booksdtos=new ArrayList<>();
		for(Book book:books) {
			BookDTO bookdto=new BookDTO();
			bookdto.setAuthor(book.getAuthor());
			bookdto.setBookname(book.getName());
			bookdto.setCategory(book.getCategory());
			bookdto.setStatus(book.getStatus());
			bookdto.setStock(book.getStock());
			booksdtos.add(bookdto);
		}
		customerdto.setBooks(booksdtos);
		return customerdto;
	}

	
}
