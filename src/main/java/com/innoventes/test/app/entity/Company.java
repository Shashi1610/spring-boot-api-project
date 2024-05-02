package com.innoventes.test.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "company")
@Entity
public class Company extends BaseEntity {

	@Id
	@SequenceGenerator(sequenceName = "company_seq", allocationSize = 1, name = "company_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
	private Long id;

	@Column(name = "company_name")
	@NotBlank(message = "cannot be empty string")
	@Min(value = 5, message= "Should be at least 5 characters of length")
	private String companyName;

	@Column(name = "email")
	@NotBlank(message = "cannot be empty string")
	@Email(message = "Should be a valid email")
	private String email;
	
	@Column(name = "strength")
	@PositiveOrZero(message = "If value is provided, it should be a positive number or zero")
	private Integer strength;
	
	@Column(name = "website_url")
	private String webSiteURL;

	@Column(name = "company_code")
	@Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}[enEN]$", message = "the format should be as follows: 1st and 2nd characters should be alphabets, 3rd and 4th characters should be numbers and the 5th character should be in E or N ")
	private String companyCode;
}
