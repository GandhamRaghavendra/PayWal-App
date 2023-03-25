package com.pws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pws.exceptions.InsufficientFundException;
import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.WalletException;
import com.pws.models.BillPayment;
import com.pws.service.BillPaymentService;

@RestController
@RequestMapping("/pws/bills")
public class BillPaymentController {

	@Autowired
	BillPaymentService billservice;
	
	
	@PostMapping("/add")
	public ResponseEntity<BillPayment> addBillCon(@RequestBody BillPayment bil,@RequestParam String key) throws InvalidCredentialsException, InsufficientFundException{
		BillPayment b = billservice.addBillPayment(bil, key);
		
		return new ResponseEntity<BillPayment>(b,HttpStatus.OK);
	}
	
	@GetMapping("/viewall")
	public ResponseEntity<List<BillPayment>> getallBills(@RequestParam String key) throws InvalidCredentialsException, WalletException{
		List<BillPayment> list = billservice.viewAllBillPayments(key);
		
		return new ResponseEntity<List<BillPayment>>(list,HttpStatus.OK);
	}
}
