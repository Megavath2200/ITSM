package com.ticketing.tool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ticketing.tool.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	public Ticket findTopByOrderByIdDesc();

	@Query("SELECT t FROM Ticket t ORDER BY t.id DESC")
	List<Ticket> findAllTicketsSorted();

	public Optional<Ticket> findByTicketId(Integer ticketId);
}
