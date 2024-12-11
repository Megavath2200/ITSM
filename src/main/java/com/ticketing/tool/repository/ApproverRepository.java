package com.ticketing.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketing.tool.entity.Approver;

@Repository
public interface ApproverRepository extends JpaRepository<Approver, Long> {

}
