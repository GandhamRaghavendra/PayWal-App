package com.pws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pws.models.BankAccount;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount,Integer>{

}
