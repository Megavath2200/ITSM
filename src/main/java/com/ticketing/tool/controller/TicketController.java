package com.ticketing.tool.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketing.tool.dto.TicketInfo;
import com.ticketing.tool.entity.Ticket;
import com.ticketing.tool.service.ITicket;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class TicketController {

	@Autowired
	private ITicket ticketService;

	@Value("${file.upload-dir}")
	private String uploadDirectory;

	@PostMapping(value = "/saveTicket")
	public ResponseEntity<Ticket> createTicket(@RequestParam("ticketInfo") String ticketInfoJson,
			@RequestParam(value = "file", required = false) MultipartFile file) throws JsonProcessingException {

		TicketInfo ticketInfo = new ObjectMapper().readValue(ticketInfoJson, TicketInfo.class);

		if (file != null && !file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			String filePath = uploadDirectory + fileName;

			File directory = new File(uploadDirectory);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			try {
				Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

			ticketInfo.setMedia(filePath);
		}

		Ticket savedTicket = ticketService.saveTicket(ticketInfo);

		return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
	}

	// @GetMapping(value = "/getAllTickets", produces = { MediaType.APPLICATION_JSON_VALUE,
	// 		MediaType.APPLICATION_XML_VALUE })
	// public ResponseEntity<?> getAllTickets() {
	// 	List<TicketInfo> tickets = ticketService.getAllTicket();
	// 	if (tickets.isEmpty()) {
	// 		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	// 	}
	// 	return new ResponseEntity<>(tickets, HttpStatus.OK);
	// }

	@GetMapping(value = "/getAllTickets", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> getAllTickets(@RequestHeader("Authorization") String token) {
        List<TicketInfo> tickets = ticketService.getAllTicket(token);
        
        if (tickets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(tickets);
    }

	@GetMapping(value = "/approver", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getApproverTickets(@RequestHeader("Authorization") String token) {
		List<TicketInfo> ticketInfoList = ticketService.getApproverTickets(token);
		return ResponseEntity.ok(ticketInfoList);
	}

	// @GetMapping(value = "/approver", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	// public ResponseEntity<?> getApproverTickets() {
	// 	List<TicketInfo> ticketInfoList = ticketService.getApproverTickets();
	// 	return ResponseEntity.ok(ticketInfoList);
	// }

	@GetMapping(value = "/next-id", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getNextTicketId() {
		try {
			Integer nextTicketId = ticketService.getTicketId();
			return new ResponseEntity<>(nextTicketId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Id not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/approver/{ticketId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<TicketInfo> getApproverTicket(@PathVariable Integer ticketId) {
		TicketInfo ticketInfo = ticketService.getApproverTicketId(ticketId);
		if (ticketInfo != null) {
			return ResponseEntity.ok(ticketInfo);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{ticketId}/{status}")
	public ResponseEntity<?> updateTicketStatus(@PathVariable Integer ticketId, @PathVariable String status) {

		try {
			ticketService.changeStaus(ticketId, status);
			return ResponseEntity.ok("Ticket status updated successfully.");
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
