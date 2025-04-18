package com.ticketing.tool.dto;

import java.sql.Timestamp;

public class TicketInfo {

	private Integer ticketId;
	private String ticketType;
	private String title;
	private String description;
	private String priority;
	private String assignedTo;
	private String supportTeam;
	private String contactType;
	private String approver;
	private Timestamp dueDate;
	private Timestamp creationDate;
	private String media;
	private String impact;
	private String contactInfo;
	private String status;
	private Timestamp raisedDate;
	private Timestamp closedDate;
	private Timestamp resolutionTime;
	private Timestamp responseTime;
	private Boolean approverCheck;
	private String createdBy;
	private String companyName;
	private String department;

	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getCreatedBy() {
		return createdBy;
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


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
