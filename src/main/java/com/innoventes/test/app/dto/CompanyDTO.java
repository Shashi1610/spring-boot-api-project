package com.innoventes.test.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CompanyDTO {

	private Long id;
	@Column(name="company_name")
	@NotBlank(message = "cannot be empty string")
	@Min(value = 5, message= "Should be at least 5 characters of length")
	private String companyName;

	@Column(name = "email")
	@NotBlank(message = "cannot be empty string")
	@Email(message = "Should be a valid email")
	private String email;

	@EvenNumberOrZero
	@Column (name = "strength")
	private Integer strength;

	@Column (name = "website_url")
	private String webSiteURL;

	@Column(name = "company_code")
	@Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}[enEN]$", message = "the format should be as follows: 1st and 2nd characters should be alphabets, 3rd and 4th characters should be numbers and the 5th character should be in E or N ")
	private String companyCode;
}
