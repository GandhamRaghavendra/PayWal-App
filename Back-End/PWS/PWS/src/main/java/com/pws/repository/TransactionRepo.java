package com.pws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pws.models.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer>{

}
