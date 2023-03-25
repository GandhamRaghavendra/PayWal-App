package com.pws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pws.models.Beneficiary;

@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary,String>{

}
