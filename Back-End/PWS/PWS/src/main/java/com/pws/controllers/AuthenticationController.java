package com.pws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pws.exceptions.CustomerException;
import com.pws.exceptions.LoginException;
import com.pws.models.Customer;
import com.pws.models.LoginDTO;
import com.pws.service.CustomerService;
import com.pws.service.LoginService;

@RestController
@RequestMapping("/pws")
public class AuthenticationController {

	@Autowired
	private LoginService lService;

	@Autowired
	private CustomerService cService;
	
	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@RequestBody Customer c) throws CustomerException{
		
		Customer cus = cService.RegisterCustomer(c);
		
		return new ResponseEntity<Customer>(cus,HttpStatus.OK);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO dto) throws LoginException{
		String mes = lService.LoginToAccount(dto);
		
		return new ResponseEntity<String>(mes,HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(@RequestParam String key) throws LoginException{
		String mes = lService.LogOutFromAccount(key);
		
		return new ResponseEntity<String>(mes,HttpStatus.OK);
	}
}
