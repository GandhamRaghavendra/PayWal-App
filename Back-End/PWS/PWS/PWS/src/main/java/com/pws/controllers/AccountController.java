package com.pws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pws.exceptions.BankAccountException;
import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.WalletException;
import com.pws.models.BankAccount;
import com.pws.models.Wallet;
import com.pws.service.AccountService;

@RestController
@RequestMapping("/pws/accservice")
public class AccountController {

	@Autowired
	private AccountService accService;
	
	@PostMapping("/accounts")
	public ResponseEntity<Wallet> addAccountCon(@RequestBody BankAccount acc,@RequestParam String key) throws BankAccountException, InvalidCredentialsException{
		Wallet wall = accService.addAccount(acc, key);
		
		return new ResponseEntity<Wallet>(wall,HttpStatus.OK);
	}
	
	@DeleteMapping("/accounts/{acc}")
	public ResponseEntity<Wallet> deleteAccountCon(@RequestParam String key,@PathVariable("acc") Integer accNo) throws BankAccountException, WalletException, InvalidCredentialsException{
		Wallet wall = accService.removeAccount(accNo, key);
		
		return new ResponseEntity<Wallet>(wall,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/accounts")
	public ResponseEntity<List<BankAccount>> getAllAccountsCon(@RequestParam String key) throws BankAccountException, InvalidCredentialsException{
		List<BankAccount> list = accService.viewAccounts(key);
		
		return new ResponseEntity<List<BankAccount>>(list,HttpStatus.OK);
	}
}
