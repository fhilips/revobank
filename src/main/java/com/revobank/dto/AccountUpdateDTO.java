package com.revobank.dto;

import java.io.Serializable;

import com.revobank.model.enums.JobTitle;
import com.revobank.model.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String birthDate;
	private JobTitle jobTitle;
	private Status status;

}
