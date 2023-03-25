package com.pws.models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillPayment {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billId;

	@NotNull(message = "Bill Type cannot be Null.")
	@NotEmpty(message = "Bill Type cannot be Empty.")
	private String billType;

	@NotNull(message = "Amount cannot be null")
	@Min(1)
	private double amount;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@NotNull
	private Date paymentDate;

	// Entity Relationships;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "walletId")
	@JsonIgnore
	private Wallet wallet;
}
