package com.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dto.BookDTO;
import com.lms.dto.CustomerDTO;
import com.lms.dto.LoginDTO;
import com.lms.dto.SignUpDTO;
import com.lms.entity.Book;
import com.lms.entity.Customer;
import com.lms.exception.InvalidDetails;
import com.lms.exception.UserAlreadyExists;
import com.lms.intf.CustomerServiceIntf;
import com.lms.repository.CustomerRepository;

@Service
public class CustomerService implements CustomerServiceIntf{
	
	@Autowired
	CustomerRepository repo;

	@Override
	public String login(LoginDTO details) throws InvalidDetails{
		// TODO Auto-generated method stub
		Customer customer = repo.findByEmail(details.getEmail());
//		System.out.println(customer);
		if(!customer.getPassword().equals(details.getPassword())) {
			throw new InvalidDetails();
		}
		return "Login Successful";
	}

	@Override
	public CustomerDTO signup(SignUpDTO details) throws UserAlreadyExists{
		// TODO Auto-generated method stub
		
		if(repo.findByEmail(details.getEmail()) != null) {
			throw new UserAlreadyExists();
		}
		
		Customer c = new Customer();
		
		c.setName(details.getName());
		c.setEmail(details.getEmail());
		c.setPassword(details.getPassword());
		c.setPhoneNumber(details.getPhoneNumber());
		c.setAddress(details.getAddress());
		
		
		repo.save(c);
		
		CustomerDTO c1=customerEntityToDTO(c);
		return c1;
		
	//	return CustomerDTO.toDTO(c);
	}

	private CustomerDTO customerEntityToDTO(Customer c) {
		CustomerDTO customerdto=new CustomerDTO();
		customerdto.setName(c.getName());
		customerdto.setEmail(c.getEmail());
		customerdto.setPhoneNumber(c.getPhoneNumber());
		customerdto.setAddress(c.getAddress());
//		List<Book> books=c.getBooks(); 
//		List<BookDTO> booksdtos=new ArrayList<>();
//		for(Book book:books) {
//			BookDTO bookdto=new BookDTO();
//			bookdto.setAuthor(book.getAuthor());
//			bookdto.setBookname(book.getName());
//			bookdto.setCategory(book.getCategory());
//			bookdto.setStatus(book.getStatus());
//			bookdto.setStock(book.getStock());
//			booksdtos.add(bookdto);
//		}
//		customerdto.setBooks(booksdtos);
		return customerdto;
	}

	@Override
	public CustomerDTO findByName(String name) {
		// TODO Auto-generated method stub
		Customer c = repo.findByName(name);
		CustomerDTO c1=customerEntityToDTO(c);
		return c1;
	}

	@Override
	public CustomerDTO findByEmail(String email) {
		// TODO Auto-generated method stub
		Customer c = repo.findByEmail(email);
		CustomerDTO c1=customerEntityToDTO(c);
		return c1;
	}

	@Override
	public CustomerDTO findByNumber(String number) {
		// TODO Auto-generated method stub
		Customer c = repo.findByPhoneNumber(number);
		CustomerDTO c1=customerEntityToDTO(c);
		return c1;
	}

	@Override
	public CustomerDTO findByAddress(String address) {
		// TODO Auto-generated method stub
		Customer c = repo.findByAddress(address);
		CustomerDTO c1=customerEntityToDTO(c);
		
		return c1;
	}

	@Override
	public CustomerDTO updateByEmail(CustomerDTO e) {
		// TODO Auto-generated method stub
		Customer c = repo.findByEmail(e.getEmail());
		c.setName(e.getName());
		c.setPhoneNumber(e.getPhoneNumber());
		c.setAddress(e.getAddress());
		CustomerDTO c1=customerEntityToDTO(c);
		return c1;
	}

	@Override
	public CustomerDTO deleteByEmail(String email) {
		// TODO Auto-generated method stub
		Customer c = repo.findByEmail(email);
		repo.delete(c);
		CustomerDTO c1=customerEntityToDTO(c);
		return c1;
	}

}
