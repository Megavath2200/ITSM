package com.ticketing.tool.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Company implements Serializable {

	private static final long serialVersionUID = 95292938842775216L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, updatable = false)
	private Long id;

	@Column(name = "COMPANY_NAME", nullable = false, length = 100)
	private String companyName;

	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@Column(name = "CONTACT_NUMBER", length = 15)
	private String contactNumber;

	@Column(name = "INCIDENT_APPROVAL")
	private Boolean incidentApproval;

	@Column(name = "CHANGE_APPROVAL")
	private Boolean changeApproval;

	@Column(name = "COMPANY_LOGO", length = 100)
	private String companyLogo;

	@Column(name = "ADDRESS", length = 255)
	private String address;

	@Column(name = "CONTRACT_DETAILS", length = 255)
	private String contractDetails;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Department> departments;


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Boolean getIncidentApproval() {
		return incidentApproval;
	}

	public void setIncidentApproval(Boolean incidentApproval) {
		this.incidentApproval = incidentApproval;
	}

	public Boolean getChangeApproval() {
		return changeApproval;
	}

	public void setChangeApproval(Boolean changeApproval) {
		this.changeApproval = changeApproval;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getContractDetails() {
		return contractDetails;
	}

	public void setContractDetails(String contractDetails) {
		this.contractDetails = contractDetails;
	}

}
