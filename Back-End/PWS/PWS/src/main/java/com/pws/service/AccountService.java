package com.pws.service;

import java.util.List;

import com.pws.exceptions.BankAccountException;
import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.WalletException;
import com.pws.models.BankAccount;
import com.pws.models.Wallet;

public interface AccountService {

	public Wallet addAccount(BankAccount account, String key) throws BankAccountException, InvalidCredentialsException;

	public Wallet removeAccount(Integer accountNo, String key) throws BankAccountException, WalletException, InvalidCredentialsException;

	public List<BankAccount> viewAccounts(String key) throws BankAccountException, InvalidCredentialsException;
}
