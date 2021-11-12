package com.revobank.dto.filters;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.revobank.model.enums.JobTitle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountFilter {

    private String name;
	private String document;	
	private JobTitle jobTitle;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
 
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate initialBirthday;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate finalBirthday;

}