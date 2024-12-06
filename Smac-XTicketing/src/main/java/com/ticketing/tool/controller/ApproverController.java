package com.ticketing.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.tool.entity.Approver;
import com.ticketing.tool.service.IApprover;

@RestController
@RequestMapping("/api/approver-level")
public class ApproverController {

	@Autowired
	private IApprover approverService;

	@PostMapping("/save")
	public ResponseEntity<?> saveApprover(@RequestBody Approver approver) {
		Approver approver2 = approverService.saveApprover(approver);
		return new ResponseEntity<>(approver2, HttpStatus.CREATED);
	}
}
