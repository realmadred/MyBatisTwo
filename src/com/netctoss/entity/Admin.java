package com.netctoss.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 管理员实体类
 * 实体类表属性
 * 与表的字段名
 * 一致
 */
public class Admin implements Serializable{

	private static final long serialVersionUID = -3916453302427988273L;
	
	//基本属性
	private Integer admin_id;
	private String admin_code;
	private String password;
	private String name;
	private String telephone;
	private String email;
	private Timestamp enrolldate;
	
	//角色集合,关联属性
	private Set<Role> roles = new HashSet<Role>();
	
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer adminId) {
		admin_id = adminId;
	}
	public String getAdmin_code() {
		return admin_code;
	}
	public void setAdmin_code(String adminCode) {
		admin_code = adminCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getEnrolldate() {
		return enrolldate;
	}
	public void setEnrolldate(Timestamp enrolldate) {
		this.enrolldate = enrolldate;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", admin_code=" + admin_code + ", password=" + password + ", name="
				+ name + ", telephone=" + telephone + ", email=" + email + ", enrolldate=" + enrolldate + "\n, roles="
				+ roles + "]\n";
	}
	
}
