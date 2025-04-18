package com.ticketing.tool.service;

import java.sql.Timestamp;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketing.tool.dto.TicketInfo;
import com.ticketing.tool.entity.Ticket;
import com.ticketing.tool.entity.User;
import com.ticketing.tool.repository.TicketRepository;

@Service("ticketService")
public class TicketService implements ITicket {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JwtService jwtService;

	// @Autowired
	// private CompanyRepository companyRepository;

	@Override
	public Ticket saveTicket(TicketInfo ticketInfo) {
		Ticket ticket = new Ticket();
		ticket.setTicketId(ticketInfo.getTicketId());
		ticket.setTicketType(ticketInfo.getTicketType());
		ticket.setTitle(ticketInfo.getTitle());
		ticket.setDescription(ticketInfo.getDescription());
		ticket.setPriority(ticketInfo.getPriority());
		ticket.setAssignedTo(ticketInfo.getAssignedTo());
		ticket.setSupportTeam(ticketInfo.getSupportTeam());
		ticket.setContactType(ticketInfo.getContactType());
		ticket.setApprover(ticketInfo.getApprover());
		ticket.setDueDate(ticketInfo.getDueDate());
		ticket.setCreationDate(new Timestamp(System.currentTimeMillis()));
		ticket.setMedia(ticketInfo.getMedia());
		ticket.setImpact(ticketInfo.getImpact());
		ticket.setContactInfo(ticketInfo.getContactInfo());
		ticket.setStatus("New");
		ticket.setRaisedDate(new Timestamp(System.currentTimeMillis()));
		ticket.setClosedDate(ticketInfo.getClosedDate());
		ticket.setResolutionTime(ticketInfo.getResolutionTime());
		ticket.setResponseTime(ticketInfo.getResponseTime());
		ticket.setApproverCheck(ticketInfo.getApproverCheck());
		ticket.setCreatedBy(ticketInfo.getCreatedBy());
	    ticket.setCompanyName(ticketInfo.getCompanyName());
		ticket.setDepartment(ticketInfo.getDepartment());

		// Company company = companyRepository.findById(ticketInfo.getCompanyId())
        // .orElseThrow(() -> new EntityNotFoundException("Company not found with ID: " + ticketInfo.getCompanyId()));
        // ticket.setCompany(company);

		return ticketRepository.save(ticket);
	}

	public List<TicketInfo> getAllTicket(String token) {
		User user = jwtService.getUserFromToken(token);
		String role = user.getRoleName();
		List<Ticket> tickets;
		if(role.equals("SUPER_ADMIN")){
			tickets = ticketRepository.findAllTicketsSorted();
		}else if(role.equals("ADMIN")){
			tickets = ticketRepository.findByCompanyName(user.getCompanyName());
		}
		// else if(role.equals("APPROVER")){
		// 	tickets = ticketRepository.findByCompanyNameAndDepartment(user.getCompanyName() , user.getDepartment());
		// }
		else{
			tickets = ticketRepository.findByCreatedBy(user.getEmail());
		}
		return tickets.stream()
		       .map(ticket -> modelMapper.map(ticket, TicketInfo.class))
			   .collect(Collectors.toList());
	}

	@Override
public List<TicketInfo> getApproverTickets(String token) {
    User user = jwtService.getUserFromToken(token);
    String role = user.getRoleName();
    List<Ticket> tickets;

    // Role-based filtering
    if ("SUPER_ADMIN".equals(role)) {
        tickets = ticketRepository.findAllTicketsSorted();
    } else if ("ADMIN".equals(role)) {
        tickets = ticketRepository.findByCompanyName(user.getCompanyName());
    } else if ("APPROVER".equals(role)) {
        tickets = ticketRepository.findByCompanyNameAndDepartment(user.getCompanyName(), user.getDepartment());
    } else {
        tickets = ticketRepository.findByCreatedBy(user.getEmail());
    }

    // Additional filtering for Incident and Change ticket types
    List<Ticket> filteredTickets = tickets.stream()
            .filter(ticket -> ticket.getTicketType() != null &&
                    ("Incident".equalsIgnoreCase(ticket.getTicketType().trim()) ||
                     "Change".equalsIgnoreCase(ticket.getTicketType().trim())))
            .collect(Collectors.toList());

    return filteredTickets.stream()
            .map(ticket -> modelMapper.map(ticket, TicketInfo.class))
            .collect(Collectors.toList());
}


	// @Override
	// public List<TicketInfo> getApproverTickets() {
	// 	List<Ticket> tickets = ticketRepository.findAll();

	// 	List<Ticket> filteredTickets = tickets.stream().filter(
	// 			ticket -> ticket.getTicketType() != null && ("Incident".equalsIgnoreCase(ticket.getTicketType().trim())
	// 					|| "Change".equalsIgnoreCase(ticket.getTicketType().trim())))
	// 			.collect(Collectors.toList());

	// 	List<TicketInfo> ticketInfoList = filteredTickets.stream()
	// 			.map(ticket -> modelMapper.map(ticket, TicketInfo.class)).collect(Collectors.toList());

	// 	return ticketInfoList;
	// }

	
	@Override
	public Integer getTicketId() {

		int currentYear = Year.now().getValue();
		Ticket lastTicket = ticketRepository.findTopByOrderByIdDesc();

		if (lastTicket != null && lastTicket.getTicketId().toString().startsWith(String.valueOf(currentYear % 100))) {

			int lastTicketNumber = Integer.parseInt(lastTicket.getTicketId().toString().substring(2));
			return Integer.parseInt(String.format("%02d%04d", currentYear % 100, lastTicketNumber + 1));

		} else {
			return Integer.parseInt(String.format("%02d%04d", currentYear % 100, 1));
		}
	}


	@Override
	public TicketInfo getApproverTicketId(Integer ticketId) {
		Optional<Ticket> ticketIds = ticketRepository.findByTicketId(ticketId);
		return ticketIds.map(ticket -> modelMapper.map(ticket, TicketInfo.class)).orElse(null);
	}

	@Override
	public void changeStaus(Integer ticketId, String status) {
		Optional<Ticket> tickets = ticketRepository.findByTicketId(ticketId);
		tickets.ifPresent(data -> {
			Ticket ticket = tickets.get();
			ticket.setStatus(status);
			ticketRepository.save(ticket);
		});
	}

}
