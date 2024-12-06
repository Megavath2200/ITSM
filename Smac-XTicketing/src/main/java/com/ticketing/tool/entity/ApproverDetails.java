package com.ticketing.tool.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "APPROVER_DETAILS")
public class ApproverDetails implements Serializable {

	private static final long serialVersionUID = 2536186190853002625L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "LEVEL", nullable = false)
	private Integer level;

	@Column(name = "APPROVER_NAME", nullable = false, length = 100)
	private String approverName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APPROVER_ID", nullable = false)
	private Approver approver;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public Approver getApprover() {
		return approver;
	}

	public void setApprover(Approver approver) {
		this.approver = approver;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
