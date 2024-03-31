package com.lms.dto;

import java.util.List;

import com.lms.entity.Book;
import com.lms.entity.Library;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
	
	@NotBlank(message = "Name is Required")
	private String bookname;
	@NotBlank(message = "Author is Required")
	private String author;
	@NotBlank(message = "Category is Required")
	private String category;
	@NotBlank(message = "Status is Required")
	private String status;
	@NotBlank(message = "Stock is Required")
	private Long stock;
	private List<CustomerDTO> customer;
//	//private List<Library> library;
//	@Transient
//	private CustomerDTO customer;
//	
//	public static BookDTO toDTO(Book b) {
//		if(b.getCustomer() == null)	return new BookDTO(b.getName(),b.getAuthor(),b.getCategory(),b.getStatus(),b.getStock(),null);
//		else return new BookDTO(b.getName(),b.getAuthor(),b.getCategory(),b.getStatus(),b.getStock(),CustomerDTO.toDTO(b.getCustomer()));
//	}

}
