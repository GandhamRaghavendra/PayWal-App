package com.pws.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

	@Id
	private Integer accountNo;
	
	@NotNull(message = "IFSC Code cannot be Null.")
	@NotEmpty(message = "IFSC Code cannot be Empty.")
	private String ifscCode;

	@NotNull(message = "Bank Name cannot be Null.")
	@NotEmpty(message = "Bank Name cannot be Empty.")
	private String bankName;
	
	@NotNull(message = "Balance cannot be null")
	@Min(0)
	private double balance;

	// Entity Relationships
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "walletId")
	@JsonIgnore
	private Wallet wallet;

	
}
