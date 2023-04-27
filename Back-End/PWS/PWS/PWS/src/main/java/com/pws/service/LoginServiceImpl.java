package com.pws.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pws.exceptions.LoginException;
import com.pws.models.CurrentUserSession;
import com.pws.models.Customer;
import com.pws.models.LoginDTO;
import com.pws.repository.CustomerRepo;
import com.pws.repository.SessionRepo;
import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerRepo cRepo;
	@Autowired
	private SessionRepo sRepo;

	@Override
	public String LoginToAccount(LoginDTO loginDTO) throws LoginException {

		Optional<Customer> cus = cRepo.findById(loginDTO.getMobile());

		if (!cus.isPresent())
			throw new LoginException("Please Enter Valid Mobile Number");

		Optional<CurrentUserSession> sess = sRepo.findById(loginDTO.getMobile());

		if (sess.isPresent() && cus.get().getPassword().equals(loginDTO.getPassword()))
			throw new LoginException("User Already LogedIn With This Number");

		if (cus.get().getPassword().equals(loginDTO.getPassword())) {
			String key = RandomString.make(6);
			
			CurrentUserSession currentUserSession = new CurrentUserSession();
			
			currentUserSession.setKey(key);
			currentUserSession.setMobile(cus.get().getMobile());
			
			sRepo.save(currentUserSession);
			
			return key;
		}
		
		throw new LoginException("Enter Valid Password");

	}

	@Override
	public String LogOutFromAccount(String Key) throws LoginException {
		CurrentUserSession sess = sRepo.findByKey(Key);
		
		if(sess==null) throw new LoginException("Customer Not Logged In with this Number");
		
		else {
			sRepo.delete(sess);
			return "Logged Out !!";
		}
	}

}
