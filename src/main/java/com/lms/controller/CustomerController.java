package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.CustomerDTO;
import com.lms.dto.LoginDTO;
import com.lms.dto.SignUpDTO;
import com.lms.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO e) {
		return new ResponseEntity<>(service.login(e),HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<CustomerDTO> signup(@RequestBody SignUpDTO e) {
//		System.out.println(e);
		return new ResponseEntity<>(service.signup(e),HttpStatus.CREATED);
	}
	
	@GetMapping("/name")
	public ResponseEntity<CustomerDTO> findByName(@RequestParam String name) {
//		System.out.println(e);
		return new ResponseEntity<>(service.findByName(name),HttpStatus.OK);
	}
	
	@GetMapping("/email")
	public ResponseEntity<CustomerDTO> findByEmail(@RequestParam String email) {
//		System.out.println(e);
		return new ResponseEntity<>(service.findByEmail(email),HttpStatus.OK);
	}
	
	@GetMapping("/number")
	public ResponseEntity<CustomerDTO> findByNumber(@RequestParam String number) {
//		System.out.println(e);
		return new ResponseEntity<>(service.findByName(number),HttpStatus.OK);
	}
	
	@GetMapping("/address")
	public ResponseEntity<CustomerDTO> findByAddress(@RequestParam String address) {
//		System.out.println(e);
		return new ResponseEntity<>(service.findByAddress(address),HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO e) {
//		System.out.println(e);
		return new ResponseEntity<>(service.updateByEmail(e),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<CustomerDTO> deleteByEmail(@RequestParam String email) {
//		System.out.println(e);
		return new ResponseEntity<>(service.deleteByEmail(email),HttpStatus.ACCEPTED);
	}

}
