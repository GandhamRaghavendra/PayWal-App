package com.pws.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.TransactionException;
import com.pws.exceptions.WalletException;
import com.pws.models.CurrentUserSession;
import com.pws.models.Customer;
import com.pws.models.Transaction;
import com.pws.models.Wallet;
import com.pws.repository.CustomerRepo;
import com.pws.repository.SessionRepo;
import com.pws.repository.TransactionRepo;
import com.pws.repository.WalletRepo;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepo tRepo;

	@Autowired
	private WalletRepo wRepo;

	@Autowired
	private CustomerRepo cRepo;

	@Autowired
	private SessionRepo session;

	@Override
	public Transaction addTransaction(Transaction tran) {
		return tRepo.save(tran);
	}

	@Override
	public List<Transaction> viewAllTransaction(String key) throws WalletException, InvalidCredentialsException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer = cRepo.findById(currSession.getMobile());

		Optional<Wallet> wall = wRepo.findById(customer.get().getWallet().getWalletId());
		
		List<Transaction> list = wall.get().getTransactions();
		
		if(list.size()==0) throw new WalletException("Not Transaction Added Into this Wallet");
		
		return list;
	}

	@Override
	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to, String key)
			throws WalletException, InvalidCredentialsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction viewTransaction(String key, Integer transactionId)
			throws TransactionException, InvalidCredentialsException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer = cRepo.findById(currSession.getMobile());

		Optional<Wallet> wall = wRepo.findById(customer.get().getWallet().getWalletId());
		
		List<Transaction> list = wall.get().getTransactions();
		
		if(list.size()==0) throw new TransactionException("Not Transaction Added Into this Wallet");
		
		for(Transaction t:list) {
			if(t.getTransactionId()==transactionId) return t;
		}
		
		throw new TransactionException("Transaction Not Found With This Id");
	}

}
