package com.ticketing.tool.service;

import java.util.List;

import com.ticketing.tool.entity.Company;

public interface ICompany {

	public Company saveCompany(Company company);

	public List<String> getAllCompanies();

	public Long getCompanyId(String companyName);
}
