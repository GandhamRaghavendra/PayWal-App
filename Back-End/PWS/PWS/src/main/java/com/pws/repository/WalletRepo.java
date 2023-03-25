package com.pws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pws.models.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet,Integer>{

}
