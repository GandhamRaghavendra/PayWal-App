package com.pws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pws.models.BillPayment;

@Repository
public interface BillPaymentRepo extends JpaRepository<BillPayment,Integer>{

}
