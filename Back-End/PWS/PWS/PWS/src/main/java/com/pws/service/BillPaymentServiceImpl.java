package com.pws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pws.exceptions.InsufficientFundException;
import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.WalletException;
import com.pws.models.BillPayment;
import com.pws.models.CurrentUserSession;
import com.pws.models.Customer;
import com.pws.models.Wallet;
import com.pws.repository.BillPaymentRepo;
import com.pws.repository.CustomerRepo;
import com.pws.repository.SessionRepo;
import com.pws.repository.WalletRepo;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {

	@Autowired
	private BillPaymentRepo billRepo;

	@Autowired
	private WalletRepo walletRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private SessionRepo session;

	@Override
	public BillPayment addBillPayment(BillPayment bill, String key)
			throws InvalidCredentialsException, InsufficientFundException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());

		Optional<Wallet> wall = walletRepo.findById(customer.get().getWallet().getWalletId());

		if (wall.get().getBalance() < bill.getAmount())
			throw new InsufficientFundException("InsufficientFund in Wallet");

		wall.get().setBalance(wall.get().getBalance() - bill.getAmount());

		BillPayment savebill = billRepo.save(bill);

		savebill.setWallet(wall.get());

		return billRepo.save(savebill);
	}

	@Override
	public List<BillPayment> viewAllBillPayments(String key) throws InvalidCredentialsException, WalletException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());

		Optional<Wallet> wall = walletRepo.findById(customer.get().getWallet().getWalletId());
		
		List<BillPayment> list = wall.get().getBillPayments();
		
		if(list.size()==0) throw new WalletException("No Bills Added to this wallet");
		
		return list;
	}

}
