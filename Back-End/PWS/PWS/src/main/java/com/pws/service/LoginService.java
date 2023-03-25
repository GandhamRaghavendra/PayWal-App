package com.pws.service;

import com.pws.exceptions.LoginException;
import com.pws.models.LoginDTO;

public interface LoginService {

	public String LoginToAccount(LoginDTO loginDTO)throws LoginException;
	public String LogOutFromAccount(String Key)throws LoginException;
}
