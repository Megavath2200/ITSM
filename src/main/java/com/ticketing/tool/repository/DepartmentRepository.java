package com.ticketing.tool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticketing.tool.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	// @Query("SELECT c.id FROM Company c WHERE c.name = :companyName")
	// List<Long> getCompanyId(@Param("companyName") String companyName);
	
	@Query("SELECT d FROM Department d WHERE d.company.id IN :companyIds")
	List<Department> findByCompanyIdIn(@Param("companyIds") Long companyIds);
	

	//    List<Department> findByCompanyId(Long companyId);
}
