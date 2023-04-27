package com.pws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.TransactionException;
import com.pws.exceptions.WalletException;
import com.pws.models.Transaction;
import com.pws.service.TransactionService;

@RestController
@RequestMapping("/pws/transactions")
public class TransactionController {


	@Autowired
	private TransactionService tService;
	
	@GetMapping("/view/{id}")
	public ResponseEntity<Transaction> viewTransactionCon(@RequestParam String key,@PathVariable("id") Integer tId) throws TransactionException, InvalidCredentialsException{
		Transaction tra = tService.viewTransaction(key, tId);
		
		return new ResponseEntity<Transaction>(tra,HttpStatus.OK);
	}
	
	@GetMapping("/viewall")
	public ResponseEntity<List<Transaction>> viewAllTransactionCon(@RequestParam String key) throws WalletException, InvalidCredentialsException{
		List<Transaction> list = tService.viewAllTransaction(key);
		
		return new ResponseEntity<List<Transaction>>(list,HttpStatus.OK);
	}
}
