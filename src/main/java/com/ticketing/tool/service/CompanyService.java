package com.ticketing.tool.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketing.tool.entity.Company;
import com.ticketing.tool.repository.CompanyRepository;

@Service("companyService")
public class CompanyService implements ICompany {

	@Autowired
	private CompanyRepository companyRepository;

	// @Override
	// @Transactional
	// public Company saveCompany(Company company) {
	// 	if (company.getDepartments() != null) {
	// 		company.getDepartments().forEach(department -> department.setCompany(company));
	// 	}

	// 	return companyRepository.save(company);
	// }

	@Override
	@Transactional
	public Company saveCompany(Company company) {
		if (company.getDepartments() != null && !company.getDepartments().isEmpty()) {
			company.getDepartments().forEach(department -> department.setCompany(company));
		}

		if (company.getTicketTypes() != null && !company.getTicketTypes().isEmpty()) {
			company.getTicketTypes().forEach(ticketType -> ticketType.setCompany(company));
		}

		return companyRepository.save(company);
	}

	@Override
	public List<String> getAllCompanies() {
		List<Company> companies = companyRepository.findAll();
		List<String> companyName = companies.stream().map(Company::getCompanyName).collect(Collectors.toList());
		return companyName;
	}

	@Override
	public Long getCompanyId(String companyName) {
		Optional<Company> company = companyRepository.getCompanyByCompanyName(companyName);
		return company.isPresent() ? company.get().getId() : null;
	}

}



