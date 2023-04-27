package com.pws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pws.exceptions.BeneficiaryNotFoundException;
import com.pws.exceptions.CustomerException;
import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.WalletException;
import com.pws.models.Beneficiary;
import com.pws.models.CurrentUserSession;
import com.pws.models.Customer;
import com.pws.models.Wallet;
import com.pws.repository.BeneficiaryRepo;
import com.pws.repository.CustomerRepo;
import com.pws.repository.SessionRepo;
import com.pws.repository.WalletRepo;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	@Autowired
	private BeneficiaryRepo bRepo;

	@Autowired
	private WalletRepo walletRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private SessionRepo session;

	@Override
	public Beneficiary addBeneficiary(Beneficiary bd, String key)
			throws CustomerException, WalletException, InvalidCredentialsException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());

		Optional<Wallet> wall = walletRepo.findById(customer.get().getWallet().getWalletId());
		
		Wallet wallet = wall.get();

		Optional<Beneficiary> ben = bRepo.findById(bd.getMobile());
		
		Optional<Customer> cus = customerRepo.findById(bd.getMobile());
		
		if(cus.isEmpty()) throw new CustomerException("User Not Present");

		if (ben.isPresent()) {
			ben.get().getWalletList().add(wallet);
			
			wallet.getBeneficiaryList().add(ben.get());
			
			return bRepo.save(ben.get());
		} 
		else {
			bd.getWalletList().add(wallet);
			
			return bRepo.save(bd);
		}
	}

	@Override
	public Beneficiary deleteBeneficiary(Beneficiary bd, String key)
			throws BeneficiaryNotFoundException, WalletException, InvalidCredentialsException {

		Optional<Beneficiary> ben = bRepo.findById(bd.getMobile());

		if (!ben.isPresent())
			throw new BeneficiaryNotFoundException("Invalid Beneficiary Details");

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());

		Optional<Wallet> wall = walletRepo.findById(customer.get().getWallet().getWalletId());

		List<Beneficiary> list = wall.get().getBeneficiaryList();

		if (list.size() == 0)
			throw new BeneficiaryNotFoundException("Beneficiary Not Found");

		boolean flag = false;

		for (Beneficiary b : list) {
			if (b.getMobile().equals(ben.get().getMobile())) {
				flag = true;
				break;
			}
		}

		if (!flag)
			throw new BeneficiaryNotFoundException("Beneficiary Not linked with this wallet");

		List<Wallet> wallList = ben.get().getWalletList();

		wallList.removeIf(w -> w.getWalletId() == wall.get().getWalletId());

		ben.get().setWalletList(wallList);

		return bRepo.save(ben.get());
	}

	@Override
	public Beneficiary viewBeneficiary(String mobile, String key)
			throws BeneficiaryNotFoundException, InvalidCredentialsException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());

		Optional<Wallet> wall = walletRepo.findById(customer.get().getWallet().getWalletId());

		List<Beneficiary> list = wall.get().getBeneficiaryList();

		if (list.size() == 0)
			throw new BeneficiaryNotFoundException("No Beneficiary Exist with this wallet");

		for (Beneficiary b : list) {
			if (b.getMobile().equals(mobile)) {
				return b;
			}
		}
		throw new BeneficiaryNotFoundException("Beneficiary Not Found");
	}

	@Override
	public List<Beneficiary> viewAllBeneficiary(String key)
			throws BeneficiaryNotFoundException, InvalidCredentialsException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session Key");

		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());

		Optional<Wallet> wall = walletRepo.findById(customer.get().getWallet().getWalletId());
		
		List<Beneficiary> list = wall.get().getBeneficiaryList();
		
		if(list.size()==0) throw new BeneficiaryNotFoundException("No Beneficiary Exist with this wallet");
		
		return list;
	}

}
