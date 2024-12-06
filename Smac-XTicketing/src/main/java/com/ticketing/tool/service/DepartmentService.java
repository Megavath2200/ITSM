package com.ticketing.tool.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketing.tool.entity.Department;
import com.ticketing.tool.repository.DepartmentRepository;

@Service("departmentService")
public class DepartmentService implements IDepartment {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<String> getDepartmentName(String companyName) {

		Long companyId = companyService.getCompanyId(companyName);
		List<Department> departments = departmentRepository.findByCompanyId(companyId);
		return departments.stream().map(Department::getDepartmentName).collect(Collectors.toList());
	}

}
