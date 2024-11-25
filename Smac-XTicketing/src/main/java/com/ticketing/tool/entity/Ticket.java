package com.ticketing.tool.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "TICKET")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TICKET_ID", nullable = false)
	private Integer ticketId;

	@Column(name = "TICKET_TYPE", length = 255)
	private String ticketType;

	@Column(name = "TITLE", length = 255)
	private String title;

	@Column(name = "DESCRIPTION", length = 255)
	private String description;

	@Column(name = "PRIORITY", length = 255)
	private String priority;

	@Column(name = "ASSIGNED_TO", length = 255)
	private String assignedTo;

	@Column(name = "SUPPORT_TEAM", length = 255)
	private String supportTeam;

	@Column(name = "CONTACT_TYPE", length = 255)
	private String contactType;

	@Column(name = "APPROVER", length = 255)
	private String approver;

	@Column(name = "DUE_DATE")
	private Timestamp dueDate;

	@Column(name = "CREATION_DATE")
	private Timestamp creationDate;

	@Column(name = "MEDIA", length = 255)
	private String media;

	@Column(name = "IMPACT", length = 255)
	private String impact;

	@Column(name = "CONTACT_INFO", length = 255)
	private String contactInfo;

	@Column(name = "STATUS", length = 255)
	private String status;

	@Column(name = "RAISED_DATE")
	private Timestamp raisedDate;

	@Column(name = "CLOSED_DATE")
	private Timestamp closedDate;

	@Column(name = "RESOLUTION_TIME")
	private Timestamp resolutionTime;

	@Column(name = "RESPONSE_TIME")
	private Timestamp responseTime;

	@Column(name = "APPROVER_CHECK")
	private Boolean approverCheck;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getSupportTeam() {
		return supportTeam;
	}

	public void setSupportTeam(String supportTeam) {
		this.supportTeam = supportTeam;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getRaisedDate() {
		return raisedDate;
	}

	public void setRaisedDate(Timestamp raisedDate) {
		this.raisedDate = raisedDate;
	}

	public Timestamp getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Timestamp closedDate) {
		this.closedDate = closedDate;
	}

	public Timestamp getResolutionTime() {
		return resolutionTime;
	}

	public void setResolutionTime(Timestamp resolutionTime) {
		this.resolutionTime = resolutionTime;
	}

	public Timestamp getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Timestamp responseTime) {
		this.responseTime = responseTime;
	}

	public Boolean getApproverCheck() {
		return approverCheck;
	}

	public void setApproverCheck(Boolean approverCheck) {
		this.approverCheck = approverCheck;
	}

}
