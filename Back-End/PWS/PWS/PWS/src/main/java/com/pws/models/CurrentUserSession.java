package com.pws.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserSession {

	@Id
	@NotNull(message = "mobile no. should not be null")
	@NotEmpty(message = "mobile no. should not be empty")
	@Size(max = 10)
	@Column(unique = true)
	private String mobile;

	@NotNull(message = "SessionKey should not be null")
	@NotEmpty(message = "SessionKey should not be empty")
	private String key;
}
