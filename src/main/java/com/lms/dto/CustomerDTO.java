package com.lms.dto;

import java.util.List;

import com.lms.entity.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	private String name;
	
	private String email;
	
	private String phoneNumber;
	
	private String address;
	
	private List<BookDTO> books;
	
//	public static CustomerDTO toDTO(Customer c) {
//		return new CustomerDTO(c.getName(),c.getEmail(),c.getPhoneNumber(),c.getAddress(),c.getBook());
//	}

}
