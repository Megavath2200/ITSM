package com.ticketing.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketing.tool.entity.Approver;
import com.ticketing.tool.repository.ApproverRepository;

@Service("approverService")
public class ApproverService implements IApprover {

	@Autowired
	private ApproverRepository approverRepository;

	@Override
	public Approver saveApprover(Approver approver) {
		if (approver.getApproverDetails() != null) {
			approver.getApproverDetails().forEach(detail -> detail.setApprover(approver));
		}
		return approverRepository.save(approver);
	}

}
