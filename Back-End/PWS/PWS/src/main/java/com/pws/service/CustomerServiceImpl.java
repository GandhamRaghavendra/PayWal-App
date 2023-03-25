package com.pws.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pws.exceptions.CustomerException;
import com.pws.models.Customer;
import com.pws.models.Wallet;
import com.pws.repository.CustomerRepo;
//import com.pws.repository.SessionRepo;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepo cRepo;
	
//	@Autowired
//	private SessionRepo sRepo;
	
	@Override
	public Customer RegisterCustomer(Customer customer) throws CustomerException {
		
		Optional<Customer> cus = cRepo.findById(customer.getMobile());
		
		if(cus.isPresent()) throw new CustomerException("Customer already present with this mobileNumber");
		
		Wallet wall = new Wallet(0);
		
		wall.setCustomer(customer);
		
		customer.setWallet(wall);
		
		return cRepo.save(customer);
		
	}

}
