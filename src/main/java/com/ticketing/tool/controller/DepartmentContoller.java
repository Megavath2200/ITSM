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
    try {
        List<String> departments = departmentService.getDepartmentName(companyName);
        System.out.println("departments: " + departments);
        return ResponseEntity.ok(departments);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Error fetching departments: " + e.getMessage());
    }
}



}
