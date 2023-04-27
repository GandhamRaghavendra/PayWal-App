package com.pws.service;

import com.pws.exceptions.CustomerException;
import com.pws.models.Customer;

public interface CustomerService {

	public Customer RegisterCustomer(Customer customer) throws CustomerException;
}
