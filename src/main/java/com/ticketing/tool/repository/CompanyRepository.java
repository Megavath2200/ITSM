package com.ticketing.tool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketing.tool.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> getCompanyByCompanyName(String companyName);
	// @Query("SELECT c.id FROM Company c WHERE c.name = :companyName")
    // List<Long> getCompanyId(@Param("companyName") String companyName);
 
}


