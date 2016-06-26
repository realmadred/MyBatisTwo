package com.netctoss.dao;

import java.util.List;

import com.netctoss.entity.Role;

/**
 * 角色dao
 * @author Madrid
 *
 */
public interface RoleDao {
	
	Role findById(int id);
	
	List<Role> findAll();
	
	Role findByName(String name);
	
	void update(Role role);
	
	void save(Role role);
	
	void delete(int id);
}
