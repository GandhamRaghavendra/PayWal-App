package com.pws.service;

import java.util.List;

import com.pws.exceptions.BeneficiaryNotFoundException;
import com.pws.exceptions.CustomerException;
import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.WalletException;
import com.pws.models.Beneficiary;

public interface BeneficiaryService {

	public Beneficiary addBeneficiary(Beneficiary bd, String key) throws CustomerException, WalletException, InvalidCredentialsException;

	public Beneficiary deleteBeneficiary(Beneficiary bd, String key) throws BeneficiaryNotFoundException, WalletException, InvalidCredentialsException;

	public Beneficiary viewBeneficiary(String mobile, String key) throws BeneficiaryNotFoundException, InvalidCredentialsException;

	public List<Beneficiary> viewAllBeneficiary(String key) throws BeneficiaryNotFoundException, InvalidCredentialsException;
}
