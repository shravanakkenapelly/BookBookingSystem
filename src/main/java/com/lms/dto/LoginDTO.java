package com.lms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	
	@Email(message = "Email is Required")
	private String email;
	@NotNull
	@Size(min=6,max=10)
	@Pattern(regexp = "^[a-zA-Z0-9]+$",message = "Password must be Alphanumeric")
	private String password;

}
