package com.ticketing.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketing.tool.entity.SupportTeam;
import com.ticketing.tool.repository.SupportTeamRepository;

@Service("supportTeamService")
public class SupportTeamService implements ISupportTeam {

	@Autowired
	private SupportTeamRepository supportTeamRepository;

	@Override
	public void saveSupportTeam(SupportTeam supportTeam) {
		supportTeamRepository.save(supportTeam);
	}

	@Override
	public List<SupportTeam> getSupportTeam() {
		return supportTeamRepository.findAll();
	}

}
