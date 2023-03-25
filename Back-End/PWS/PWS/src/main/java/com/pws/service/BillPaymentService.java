package com.pws.service;

import java.util.List;

import com.pws.exceptions.InsufficientFundException;
import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.WalletException;
import com.pws.models.BillPayment;

public interface BillPaymentService {

	public BillPayment addBillPayment(BillPayment bill, String key) throws InvalidCredentialsException, InsufficientFundException;

	public List<BillPayment> viewAllBillPayments(String key) throws InvalidCredentialsException, WalletException;
	
}
