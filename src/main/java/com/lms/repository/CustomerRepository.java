package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Customer findByName(String name);
	Customer findByEmail(String email);
	Customer findByPhoneNumber(String number);
	Customer findByAddress(String address);

}
