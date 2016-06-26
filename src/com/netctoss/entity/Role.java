package com.netctoss.entity;

/**
 * 管理员角色
 */
import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable{
	
	private static final long serialVersionUID = -4865106514974702929L;
	//id
	private Integer role_id;
	//角色名
	private String name;
	
	//拥有的权限
	private Set<String> modules;
	
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<String> getModules() {
		return modules;
	}
	public void setModules(Set<String> modules) {
		this.modules = modules;
	}
	@Override
	public String toString() {
		return "Role [modules=" + modules + ", name=" + name + ", role_id="
				+ role_id + "]";
	}
	
}
