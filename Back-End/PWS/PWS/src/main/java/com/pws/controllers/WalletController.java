package com.pws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pws.exceptions.CustomerException;
import com.pws.exceptions.InsufficientFundException;
import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.WalletException;
import com.pws.models.BankAccount;
import com.pws.models.Customer;
import com.pws.models.Wallet;
import com.pws.service.WalletService;

@RestController
@RequestMapping("/pws/wallet")
public class WalletController {

	
	@Autowired
	private WalletService wService;
	
	@GetMapping("/viewbal")
	public ResponseEntity<Wallet> showBalanceCon(@RequestParam 	String key) throws CustomerException, InvalidCredentialsException{
		Wallet wall = wService.showBalance(key);
		
		return new ResponseEntity<Wallet>(wall,HttpStatus.OK);
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<String> fundTransferCon(@RequestParam String tmob,@RequestParam double amount,@RequestParam String key) throws CustomerException, InsufficientFundException, InvalidCredentialsException{
		String mes = wService.fundTransfer(tmob, amount, key);
		
		return new ResponseEntity<String>(mes,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateAccountCon(@RequestBody Customer cus,@RequestParam String kye) throws CustomerException, InvalidCredentialsException{
		Customer c = wService.updateAccount(cus, kye);
		
		return new ResponseEntity<Customer>(c,HttpStatus.ACCEPTED);
	}
		
	@PostMapping("/deposit")
	public ResponseEntity<Wallet> AddMoneyHandler(@RequestParam double amount, @RequestParam String key,
			@RequestBody BankAccount acc) throws CustomerException, WalletException, InvalidCredentialsException {
		Wallet wallet = wService.addMoney(key, amount, acc);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}
	
	
}
