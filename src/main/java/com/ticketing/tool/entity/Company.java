package com.ticketing.tool.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@Column(name = "COMPANY_REGNO", length = 255)
	private String companyRegNo;

	@Column(name = "INDUSTRY_TPE", length = 255)
	private String industryType;

	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@Column(name = "COMPANY_ADDRESS", length = 255)
	private String companyAddress;

	@Column(name = "CITY", length = 255)
	private String city;

	@Column(name = "COUNTRY", length = 255)
	private String country;

	@Column(name = "STATE", length = 255)
    private String state;

	@Column(name = "POSTAL_CODE", length = 255)
    private String postalCode;

	@Column(name = "PRINARY_CONTACT_NAME", length = 255)
	private String primaryContactName;

	@Column(name = "PRIMARY_CONTACT_EMAIL", length = 255)
    private String primaryContactEmail;

	@Column(name = "PRIMARY_CONTACT_PHONE", length = 255)
    private String primaryContactPhone;

	@Column(name = "SECONDARY_CONTACT_NAME", length = 255)
	private String secondaryContactName;

	@Column(name = "SECONDARY_CONTACT_EMAIL", length = 255)
    private String secondaryContactEmail;

	@Column(name = "SECONDARY_CONTACT_PHONE", length = 255)
    private String secondaryContactPhone;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference
	private List<Department> departments;
	

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference
	private List<TicketType> ticketTypes;


	// @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	// @JsonManagedReference
	// private List<User> users;

	// @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	// @JsonManagedReference
	// private List<Ticket> tickets;

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


	public String getCompanyRegNo() {
		return companyRegNo;
	}


	public void setCompanyRegNo(String companyRegNo) {
		this.companyRegNo = companyRegNo;
	}


	public String getIndustryType() {
		return industryType;
	}


	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCompanyAddress() {
		return companyAddress;
	}


	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public String getPrimaryContactName() {
		return primaryContactName;
	}


	public void setPrimaryContactName(String primaryContactName) {
		this.primaryContactName = primaryContactName;
	}


	public String getPrimaryContactEmail() {
		return primaryContactEmail;
	}


	public void setPrimaryContactEmail(String primaryContactEmail) {
		this.primaryContactEmail = primaryContactEmail;
	}


	public String getPrimaryContactPhone() {
		return primaryContactPhone;
	}


	public void setPrimaryContactPhone(String primaryContactPhone) {
		this.primaryContactPhone = primaryContactPhone;
	}


	public String getSecondaryContactName() {
		return secondaryContactName;
	}


	public void setSecondaryContactName(String secondaryContactName) {
		this.secondaryContactName = secondaryContactName;
	}


	public String getSecondaryContactEmail() {
		return secondaryContactEmail;
	}


	public void setSecondaryContactEmail(String secondaryContactEmail) {
		this.secondaryContactEmail = secondaryContactEmail;
	}


	public String getSecondaryContactPhone() {
		return secondaryContactPhone;
	}


	public void setSecondaryContactPhone(String secondaryContactPhone) {
		this.secondaryContactPhone = secondaryContactPhone;
	}


	public List<Department> getDepartments() {
		return departments;
	}


	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}


	public List<TicketType> getTicketTypes() {
		return ticketTypes;
	}


	public void setTicketTypes(List<TicketType> ticketTypes) {
		this.ticketTypes = ticketTypes;
	}
}

  

    

  

    // @ElementCollection
    // private List<String> allowedCategories;

    // @ElementCollection
    // private List<String> departments;

   