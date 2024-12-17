package com.ticketing.tool.service;

import java.util.List;

import com.ticketing.tool.entity.ContractType;

public interface IContractType {

	public void saveContractType(ContractType contractType);

	public List<ContractType> getContractType();
}
