package com.lms.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
	@NotBlank(message = "Name is Required")
	private String name;
	@Email(message = "Email is Required")
	private String email;
	@Pattern(regexp = "^[a-zA-Z0-9]+$",message = "Password must be Alphanumeric")
	@Size(min=6,max=10)
	private String password;
	@Pattern(regexp = "\\d{10}")
	private String phoneNumber;
	@NotBlank(message = "Address is Required")
	private String address;
	

}
