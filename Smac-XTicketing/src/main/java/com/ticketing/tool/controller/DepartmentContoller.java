package com.ticketing.tool.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.tool.service.IDepartment;

@RestController
@RequestMapping("/api/department")
public class DepartmentContoller implements Serializable {

	private static final long serialVersionUID = -3332052707951505714L;

	@Autowired
	private IDepartment departmentService;

	@GetMapping("/deptName")
	public ResponseEntity<?> getDepartmentName(@RequestParam String companyName) {
		List<String> departmets = departmentService.getDepartmentName(companyName);
		return new ResponseEntity<>(departmets, HttpStatus.OK);
	}

}
