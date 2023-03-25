package com.pws.service;

import java.time.LocalDate;
import java.util.List;

import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.TransactionException;
import com.pws.exceptions.WalletException;
import com.pws.models.Transaction;

public interface TransactionService {

	public Transaction addTransaction(Transaction tran);

	public List<Transaction> viewAllTransaction(String key) throws WalletException, InvalidCredentialsException;

	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to, String key) throws WalletException, InvalidCredentialsException;

	public Transaction viewTransaction(String key, Integer transactionId) throws TransactionException, InvalidCredentialsException;
}
