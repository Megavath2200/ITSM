package com.ticketing.tool.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.tool.entity.Status;
import com.ticketing.tool.service.IStatus;

@RestController
@RequestMapping("api/status")
public class StatusContoller implements Serializable {

	private static final long serialVersionUID = 7781559582510104567L;

	@Autowired
	private IStatus statusService;

	@GetMapping("/getStatus")
	public ResponseEntity<?> getAllStatus() {
		List<Status> status = statusService.getAllStatus();
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}
