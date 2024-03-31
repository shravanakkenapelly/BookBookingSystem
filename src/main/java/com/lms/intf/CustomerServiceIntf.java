package com.lms.intf;

import com.lms.dto.CustomerDTO;
import com.lms.dto.LoginDTO;
import com.lms.dto.SignUpDTO;

public interface CustomerServiceIntf {
	
//	Login 
	String login(LoginDTO details);
	
//	SignUp
	CustomerDTO signup(SignUpDTO details);
	
//	Find
	CustomerDTO findByName(String name);
	CustomerDTO findByEmail(String email);
	CustomerDTO findByNumber(String number);
	CustomerDTO findByAddress(String address);
	
//	Update 
	CustomerDTO updateByEmail(CustomerDTO e);
	
//	Delete
	CustomerDTO deleteByEmail(String email);
	

}
