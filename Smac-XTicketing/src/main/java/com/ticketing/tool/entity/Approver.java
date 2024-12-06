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
@Table(name = "APPROVER")
public class Approver implements Serializable {

	private static final long serialVersionUID = 6241834231822455419L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, updatable = false)
	private Long id;

	@Column(name = "COMPANY_NAME", nullable = false, length = 100)
	private String companyName;

	@Column(name = "DEPARTMENT_NAME", nullable = false, length = 100)
	private String departmentName;

//	@Column(name = "APPROVER_LEVEL", nullable = false)
//	private Integer approverLevel;

	@Column(name = "APPROVER_LEVEL", nullable = false)
	private Integer approverLevel;

	@OneToMany(mappedBy = "approver", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<ApproverDetails> approverDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void setApproverLevel(Integer approverLevel) {
		this.approverLevel = approverLevel;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getApproverLevel() {
		return approverLevel;
	}

	public void setApproverLevel(int approverLevel) {
		this.approverLevel = approverLevel;
	}

	public List<ApproverDetails> getApproverDetails() {
		return approverDetails;
	}

	public void setApproverDetails(List<ApproverDetails> approverDetails) {
		this.approverDetails = approverDetails;
	}

}
