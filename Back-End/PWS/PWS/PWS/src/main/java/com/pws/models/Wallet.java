package com.pws.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer walletId;

	@NotNull(message = "balance cannot be Null.")
	@Min(0)
	private double balance;

//	 Entity Relationships
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private List<BankAccount> bankAccounts = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private List<Transaction> transactions = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private List<BillPayment> billPayments = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "walletList")
	private List<Beneficiary> beneficiaryList = new ArrayList<>();

	public Wallet(@NotNull(message = "balance cannot be Null.") @Min(0) double balance) {
		super();
		this.balance = balance;
	}
	
	

}
