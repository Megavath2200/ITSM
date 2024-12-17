package com.ticketing.tool.service;

import java.util.List;

import com.ticketing.tool.entity.SupportTeam;

public interface ISupportTeam {

	public void saveSupportTeam(SupportTeam supportTeam);

	public List<SupportTeam> getSupportTeam();
}
