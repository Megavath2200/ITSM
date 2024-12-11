package com.ticketing.tool.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.tool.entity.Company;
import com.ticketing.tool.service.ICompany;

@RestController
@RequestMapping("/api/company")
public class CompanyController implements Serializable {

	private static final long serialVersionUID = 4552322646399393610L;

	@Autowired
	private ICompany companyService;

	@PostMapping("/saveCompany")
	public ResponseEntity<?> saveCompany(@RequestBody Company company) {
		Company company2 = companyService.saveCompany(company);
		return new ResponseEntity<>(company2, HttpStatus.CREATED);
	}

	@GetMapping("/getCompany")
	public ResponseEntity<?> getCompany() {
		List<String> allCompanies = companyService.getAllCompanies();
		return new ResponseEntity<>(allCompanies, HttpStatus.OK);
	}

}
