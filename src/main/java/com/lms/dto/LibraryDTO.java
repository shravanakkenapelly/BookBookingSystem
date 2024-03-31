package com.lms.dto;

import java.util.List;

import com.lms.entity.Book;
import com.lms.entity.Customer;
import com.lms.entity.Library;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDTO {
	@NotBlank(message = "Name is Required")
	private String libraryname;
	@NotBlank(message = "Location is Required")
	private String librarylocation;

	
}
