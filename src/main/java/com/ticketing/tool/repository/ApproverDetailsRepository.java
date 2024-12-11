package com.ticketing.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketing.tool.entity.ApproverDetails;

@Repository
public interface ApproverDetailsRepository extends JpaRepository<ApproverDetails, Long> {

}
