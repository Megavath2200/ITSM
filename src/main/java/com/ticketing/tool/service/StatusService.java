package com.ticketing.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketing.tool.entity.Status;
import com.ticketing.tool.repository.StatusRepository;

@Service("statusService")
public class StatusService implements IStatus {

	@Autowired
	private StatusRepository statusRepository;

	@Override
	public List<Status> getAllStatus() {
		return statusRepository.findAll();
	}

}
