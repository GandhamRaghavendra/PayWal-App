package com.pws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pws.exceptions.BankAccountException;
import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.WalletException;
import com.pws.models.BankAccount;
import com.pws.models.CurrentUserSession;
import com.pws.models.Customer;
import com.pws.models.Wallet;
import com.pws.repository.BankAccountRepo;
//import com.pws.repository.BankAccountRepo;
import com.pws.repository.CustomerRepo;
import com.pws.repository.SessionRepo;
import com.pws.repository.WalletRepo;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private BankAccountRepo brepo;

	@Autowired
	private WalletRepo wRepo;

	@Autowired
	private SessionRepo session;

	@Autowired
	private CustomerRepo cRepo;

	@Override
	public Wallet addAccount(BankAccount account, String key) throws BankAccountException, InvalidCredentialsException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer = cRepo.findById(currSession.getMobile());

		Optional<Wallet> wall = wRepo.findById(customer.get().getWallet().getWalletId());

		List<BankAccount> list = wall.get().getBankAccounts();

		boolean flag = false;

		if (list.size() != 0) {
			for (BankAccount acc : list) {
				if (acc.getAccountNo().equals(account.getAccountNo())) {
					flag = true;
					break;
				}
			}

			if (flag)
				throw new BankAccountException("BankAcc added already");
		}
		
			account.setWallet(wall.get());
			
			wall.get().getBankAccounts().add(account);

		return wRepo.save(wall.get());

	}

	@Override
	public Wallet removeAccount(Integer accountNo, String key)
			throws BankAccountException, WalletException, InvalidCredentialsException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer = cRepo.findById(currSession.getMobile());

		Optional<Wallet> wall = wRepo.findById(customer.get().getWallet().getWalletId());
		
		if(!wall.isPresent()) throw new WalletException("Unknowen User");
		
		Wallet wallet = wall.get();
		
		List<BankAccount> list = wallet.getBankAccounts();
		
		boolean flag = false;
		
		if(list.size()!=0) {
			for (BankAccount acc : list) {
				if (acc.getAccountNo().equals(accountNo)) {
					flag = true;
					break;
				}
			}			
			if(!flag) throw new BankAccountException("Given Account Not Linked with Wallet");
		}
		

		
		Optional<BankAccount> b = brepo.findById(accountNo);
		BankAccount bank = b.get();
		bank.setWallet(null);
		brepo.delete(bank);
		
		list.removeIf(ac->ac.getAccountNo().equals(accountNo));
		
		wallet.setBankAccounts(list);
		
		return wRepo.save(wallet);
	}

	@Override
	public List<BankAccount> viewAccounts(String key) throws BankAccountException, InvalidCredentialsException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer = cRepo.findById(currSession.getMobile());

		Optional<Wallet> wall = wRepo.findById(customer.get().getWallet().getWalletId());
		
		List<BankAccount> list = wall.get().getBankAccounts();
		
		if(list.size()==0) throw new BankAccountException("No Account Found");	
		
		return list;
	}

}
