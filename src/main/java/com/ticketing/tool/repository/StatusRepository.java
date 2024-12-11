package com.ticketing.tool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketing.tool.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
