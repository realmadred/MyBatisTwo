package com.netctoss.entity;

import java.io.Serializable;

public class AdminRole implements Serializable {

	private static final long serialVersionUID = 200723696228479227L;
	
	private Integer admin_id;
	private Integer role_id;
	
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
}
