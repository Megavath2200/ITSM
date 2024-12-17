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

import com.ticketing.tool.entity.ContractType;
import com.ticketing.tool.service.IContractType;

@RestController
@RequestMapping("/api/contractType")
public class ContractTypeController implements Serializable {

	private static final long serialVersionUID = 8766871474096206712L;

	@Autowired
	private IContractType contractTypeService;

	@PostMapping("/saveContractType")
	public ResponseEntity<?> saveContractType(@RequestBody ContractType contractType) {
		contractTypeService.saveContractType(contractType);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/getContractType")
	public ResponseEntity<?> getContractType() {
		List<ContractType> contractType = contractTypeService.getContractType();
		return new ResponseEntity<>(contractType, HttpStatus.OK);
	}
}
