package com.pws.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	//Custom Exception Handler
	
	@ExceptionHandler(BankAccountException.class)
	public ResponseEntity<MyErrorDetails> myBankException(BankAccountException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BeneficiaryNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myBeneficiaryNotFoundException(BeneficiaryNotFoundException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> myCustomerException(CustomerException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InsufficientFundException.class)
	public ResponseEntity<MyErrorDetails> myInsufficientFundException(InsufficientFundException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<MyErrorDetails> myInvalidCredentialsException(InvalidCredentialsException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> myLoginException(LoginException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<MyErrorDetails> myTransactionException(TransactionException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(WalletException.class)
	public ResponseEntity<MyErrorDetails> myWalletException(WalletException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	//Custom Exception Handler
	
	
	
	
	
	
//	Default Exception
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerException(NoHandlerFoundException e,WebRequest re){
			MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
			
			return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> anyException(Exception e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> manvException(MethodArgumentNotValidException e,WebRequest re){
		MyErrorDetails err = new MyErrorDetails(LocalDate.now(),e.getMessage(),re.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	
}
