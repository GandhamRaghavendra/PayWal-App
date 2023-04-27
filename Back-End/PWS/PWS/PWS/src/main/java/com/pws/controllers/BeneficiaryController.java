package com.pws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pws.exceptions.BeneficiaryNotFoundException;
import com.pws.exceptions.CustomerException;
import com.pws.exceptions.InvalidCredentialsException;
import com.pws.exceptions.WalletException;
import com.pws.models.Beneficiary;
import com.pws.service.BeneficiaryService;

@RestController
@RequestMapping("/pws/beneficiary")
public class BeneficiaryController {

	@Autowired
	private BeneficiaryService benService;

	@PostMapping("/add")
	public ResponseEntity<Beneficiary> addBeneficiaryCon(@RequestBody Beneficiary b, @RequestParam String key)
			throws CustomerException, WalletException, InvalidCredentialsException {
		Beneficiary ben = benService.addBeneficiary(b, key);

		return new ResponseEntity<Beneficiary>(ben, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Beneficiary> deleteBeneficiaryCon(@RequestBody Beneficiary b, @RequestParam String key)
			throws BeneficiaryNotFoundException, WalletException, InvalidCredentialsException {
		Beneficiary ben = benService.deleteBeneficiary(b, key);

		return new ResponseEntity<Beneficiary>(ben, HttpStatus.OK);
	}

	@GetMapping("/view/{mob}")
	public ResponseEntity<Beneficiary> viewBeneficiaryCon(@PathVariable("mob") String mob, @RequestParam String key)
			throws BeneficiaryNotFoundException, InvalidCredentialsException {
		Beneficiary ben = benService.viewBeneficiary(mob, key);

		return new ResponseEntity<Beneficiary>(ben, HttpStatus.OK);
	}

	@GetMapping("/viewall/")
	public ResponseEntity<List<Beneficiary>> viewBeneficiaryCon(@RequestParam String key) throws BeneficiaryNotFoundException, InvalidCredentialsException{
		List<Beneficiary> list = benService.viewAllBeneficiary(key);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
