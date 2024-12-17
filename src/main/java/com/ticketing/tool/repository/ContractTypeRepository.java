package com.ticketing.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketing.tool.entity.ContractType;

public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {

}
