package com.ticketing.tool.dto;

import java.io.Serializable;

public class WenderInfo implements Serializable {

	private static final long serialVersionUID = 19241593719298699L;

	private Integer wenderId;

	private String wenderName;

	public Integer getWenderId() {
		return wenderId;
	}

	public void setWenderId(Integer wenderId) {
		this.wenderId = wenderId;
	}

	public String getWenderName() {
		return wenderName;
	}

	public void setWenderName(String wenderName) {
		this.wenderName = wenderName;
	}

}
