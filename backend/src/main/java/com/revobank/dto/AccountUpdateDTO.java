package com.revobank.dto;

import java.io.Serializable;

import com.revobank.model.enums.JobTitle;
import com.revobank.model.enums.Status;

public class AccountUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String birthDate;
	private JobTitle jobTitle;
	private Status status;

	public AccountUpdateDTO() {
	}

	public AccountUpdateDTO(String name, String birthDate, JobTitle jobTitle, Status status) {
		this.name = name;
		this.birthDate = birthDate;
		this.jobTitle = jobTitle;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public JobTitle getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
