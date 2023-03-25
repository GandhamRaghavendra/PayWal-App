package com.pws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pws.models.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,String>{

}
