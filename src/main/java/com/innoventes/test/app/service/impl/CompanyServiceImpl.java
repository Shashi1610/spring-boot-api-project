package com.innoventes.test.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.innoventes.test.app.dto.CompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innoventes.test.app.entity.Company;
import com.innoventes.test.app.error.ApplicationErrorCodes;
import com.innoventes.test.app.exception.ResourceNotFoundException;
import com.innoventes.test.app.exception.ValidationException;
import com.innoventes.test.app.repository.CompanyRepository;
import com.innoventes.test.app.service.CompanyService;
import com.innoventes.test.app.util.ServiceHelper;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private ServiceHelper serviceHelper;

	@Override
	public List<Company> getAllCompanies() {
		ArrayList<Company> companyList = new ArrayList<Company>();
		companyRepository.findAll().forEach(companyList::add);
		return companyList;
	}

	@Override
	public Company addCompany(Company company) throws ValidationException {
		return companyRepository.save(company);
	}

	@Override
	public Company updateCompany(Long id, Company company) throws ValidationException {
		Company existingCompanyRecord = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(serviceHelper.getLocalizedMessage(ApplicationErrorCodes.COMPANY_NOT_FOUND), id),
						ApplicationErrorCodes.COMPANY_NOT_FOUND));
		company.setId(existingCompanyRecord.getId());
		return companyRepository.save(company);
	}

	@Override
	public void deleteCompany(Long id) {
		Company existingCompanyRecord = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(serviceHelper.getLocalizedMessage(ApplicationErrorCodes.COMPANY_NOT_FOUND), id),
						ApplicationErrorCodes.COMPANY_NOT_FOUND));
		companyRepository.deleteById(existingCompanyRecord.getId());
	}

	@Override
	public CompanyDTO findCompanyById(Long id) {
		Company company=companyRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("company not found with id :"+id,"COMPANY_NOT_FOUND"));
		CompanyDTO dto = new CompanyDTO();
		dto.setCompanyName(company.getCompanyName());
		dto.setEmail(company.getEmail());
		dto.setId(company.getId());
		dto.setCompanyCode(company.getCompanyCode());
		dto.setStrength(company.getStrength());
		dto.setWebSiteURL(company.getWebSiteURL());
		return dto;
	}

	@Override
	public CompanyDTO findbyCompanyCode(String companyCode) {
		Company company=companyRepository.findByCompanyCode(companyCode).get();
		CompanyDTO dto = new CompanyDTO();
		dto.setCompanyName(company.getCompanyName());
		dto.setEmail(company.getEmail());
		dto.setId(company.getId());
		dto.setStrength(company.getStrength());
		dto.setWebSiteURL(company.getWebSiteURL());
		return dto;
	}

	@Override
	public CompanyDTO partialUpdateCompany(Long id, CompanyDTO companyDTO) {

		Company company=new Company();
		company.setId(companyDTO.getId());
		Company savedCompany=companyRepository.save(company);
		CompanyDTO dto = new CompanyDTO();
		dto.setId(savedCompany.getId());
		return dto;
	}
}
