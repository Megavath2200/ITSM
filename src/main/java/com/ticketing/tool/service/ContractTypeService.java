package com.ticketing.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketing.tool.entity.ContractType;
import com.ticketing.tool.repository.ContractTypeRepository;

@Service("contractTypeService")
public class ContractTypeService implements IContractType {

	@Autowired
	private ContractTypeRepository contractTypeRepository;

	@Override
	public void saveContractType(ContractType contractType) {
		contractTypeRepository.save(contractType);
	}

	@Override
	public List<ContractType> getContractType() {
		return contractTypeRepository.findAll();
	}

}
