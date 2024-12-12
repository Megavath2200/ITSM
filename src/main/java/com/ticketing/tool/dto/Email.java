package com.ticketing.tool.dto;

import java.util.ArrayList;

import org.springframework.core.io.Resource;

public class Email {

	private String to;
	private String subject;
	private String body;
	private String cc;
	private String bcc;
	private String imagePath;
	private ArrayList<String> attachments;
	private Resource pdfResource;
	private String companyName;
	private Resource excelResource;

	public Resource getExcelResource() {
		return excelResource;
	}

	public void setExcelResource(Resource excelResource) {
		this.excelResource = excelResource;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public ArrayList<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(ArrayList<String> attachments) {
		this.attachments = attachments;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Resource getPdfResource() {
		return pdfResource;
	}

	public void setPdfResource(Resource pdfResource) {
		this.pdfResource = pdfResource;
	}

}