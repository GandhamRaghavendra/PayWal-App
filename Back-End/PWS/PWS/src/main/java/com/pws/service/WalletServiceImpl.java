package com.pws.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pws.exceptions.CustomerException;
import com.pws.exceptions.InsufficientFundException;
import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.WalletException;
import com.pws.models.BankAccount;
import com.pws.models.CurrentUserSession;
import com.pws.models.Customer;
import com.pws.models.Transaction;
import com.pws.models.Wallet;
import com.pws.repository.BankAccountRepo;
import com.pws.repository.CustomerRepo;
import com.pws.repository.SessionRepo;
import com.pws.repository.TransactionRepo;
import com.pws.repository.WalletRepo;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepo wRepo;

	@Autowired
	private CustomerRepo cRepo;

	@Autowired
	private BankAccountRepo bRepo;

	@Autowired
	private TransactionRepo tRepo;

	@Autowired
	private SessionRepo session;

	@Override
	public Wallet showBalance(String key) throws CustomerException, InvalidCredentialsException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer = cRepo.findById(currSession.getMobile());

		Optional<Wallet> wall = wRepo.findById(customer.get().getWallet().getWalletId());

		return wall.get();

	}

	@Override
	public String fundTransfer(String targetMobileNo, double amount, String key)
			throws CustomerException, InsufficientFundException, InvalidCredentialsException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer1 = cRepo.findById(currSession.getMobile());

		if (!customer1.isPresent())
			throw new CustomerException("Sender account not found");

		Optional<Customer> customer2 = cRepo.findById(targetMobileNo);

		if (!customer2.isPresent())
			throw new CustomerException("Receiver account not found");

		if (customer1.get().getWallet().getBalance() < amount)
			throw new InsufficientFundException("Insufficient Balance in your wallet");

		// Sender Transaction
		Transaction tran1 = new Transaction();

		tran1.setAmount(amount);
		tran1.setTransactionDate(LocalDate.now());
		tran1.setDescription("Fund Transferd to" + customer2.get().getName());
		tran1.setTransactionType("Fund Transfer(W-W)");
		tran1.setWallet(customer1.get().getWallet());

		tRepo.save(tran1);

		// Receiver Transaction
		Transaction tran2 = new Transaction();

		tran2.setAmount(amount);
		tran2.setDescription("Fund Received From" + customer1.get().getName());
		tran2.setTransactionDate(LocalDate.now());
		tran2.setTransactionType("Fund Transfer(W-W)");
		tran2.setWallet(customer2.get().getWallet());

		tRepo.save(tran2);

		double updatebal1 = customer1.get().getWallet().getBalance() - amount;

		customer1.get().getWallet().setBalance(updatebal1);

		cRepo.save(customer1.get());

		double updatebal2 = customer2.get().getWallet().getBalance() + amount;

		customer2.get().getWallet().setBalance(updatebal2);

		cRepo.save(customer2.get());

		return "Fund Transfered successfully";

	}

	@Override
	public Customer updateAccount(Customer customer, String key) throws CustomerException, InvalidCredentialsException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer1 = cRepo.findById(currSession.getMobile());

		if (!customer1.isPresent())
			throw new CustomerException("Customer Not Found");

		if (customer.getName() != null) {
			customer1.get().setName(customer.getName());
		}
		if (customer.getPassword() != null) {
			customer1.get().setPassword(customer.getPassword());
		}

		return cRepo.save(customer1.get());
	}

	@Override
	public Wallet addMoney(String key, double amount, BankAccount acc)
			throws WalletException, InvalidCredentialsException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer1 = cRepo.findById(currSession.getMobile());
		
		Optional<Wallet> wall = wRepo.findById(customer1.get().getWallet().getWalletId());
		
		Optional<BankAccount> account = bRepo.findById(acc.getAccountNo());
		
		if(!account.isPresent()||account.get().getWallet().getWalletId()!=wall.get().getWalletId()) throw new WalletException("Account Not Found");
		
		Transaction tran = new Transaction();
		
		tran.setAmount(amount);
		tran.setTransactionDate(LocalDate.now());
		tran.setDescription("Fund Added From Bank Account"+ acc.getAccountNo());
		tran.setTransactionType("Self Deposit");
		tran.setWallet(wall.get());
		
		tRepo.save(tran);
		
		account.get().setBalance(account.get().getBalance()-amount);
		
		wall.get().setBalance(wall.get().getBalance()+amount);
		
		bRepo.save(account.get());
		
		return wRepo.save(wall.get());
		
	}

}
