package com.pws.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@NotNull(message = "mobile no. should not be null")
	@NotEmpty(message = "mobile no. should not be empty")
	@Size(max = 10)
	private String mobile;

	@NotNull(message = "name should not be null")
	@NotEmpty(message = "name cannot be null")
	@Size(min = 3, max = 25, message = "name length should be between 3 to 25")
	private String name;

	
	@NotNull(message = "password no. should not be null")
	@NotEmpty(message = "password no. should not be empty")
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Wallet wallet;
}
