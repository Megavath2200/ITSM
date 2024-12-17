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

import com.ticketing.tool.entity.SupportTeam;
import com.ticketing.tool.service.ISupportTeam;

@RestController
@RequestMapping("/api/supportTeam")
public class SupportTeamController implements Serializable {

	private static final long serialVersionUID = -2882182703098139700L;

	@Autowired
	private ISupportTeam supportTeamService;

	@PostMapping("/saveSupportTeam")
	public ResponseEntity<?> saveSupportTeam(@RequestBody SupportTeam supportTeam) {
		supportTeamService.saveSupportTeam(supportTeam);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/getSupportTeam")
	public ResponseEntity<?> getImpact() {
		List<SupportTeam> supportTeam = supportTeamService.getSupportTeam();
		return new ResponseEntity<>(supportTeam, HttpStatus.OK);
	}
}
