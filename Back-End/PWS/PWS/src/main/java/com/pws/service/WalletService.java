package com.pws.service;

import com.pws.exceptions.BankAccountException;
import com.pws.exceptions.CustomerException;
import com.pws.exceptions.InsufficientFundException;
import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.WalletException;
import com.pws.models.BankAccount;
import com.pws.models.Customer;
import com.pws.models.Wallet;

public interface WalletService {

	public Wallet showBalance(String key) throws CustomerException, InvalidCredentialsException;

	public String fundTransfer(String targetMobileNo, double amount, String key) throws CustomerException, InsufficientFundException, InvalidCredentialsException;

	public Customer updateAccount(Customer customer, String key) throws CustomerException, InvalidCredentialsException;

	public Wallet addMoney(String key, double amount, BankAccount acc) throws WalletException, InvalidCredentialsException, BankAccountException;
}
