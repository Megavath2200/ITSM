package com.ticketing.tool.service;

import java.util.List;

import com.ticketing.tool.dto.TicketInfo;
import com.ticketing.tool.entity.Ticket;

public interface ITicket {

	public Ticket saveTicket(TicketInfo ticketInfo);

	public List<TicketInfo> getAllTicket(String token);

	public Integer getTicketId();

	public List<TicketInfo> getApproverTickets(String token);

	public TicketInfo getApproverTicketId(Integer ticketId);

	public void changeStaus(Integer ticketId, String status);
}
