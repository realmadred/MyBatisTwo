package com.netctoss.dao;

import java.util.List;

import com.netctoss.entity.Admin;

/**
 * 管理员dao
 * @author Madrid
 *
 */
public interface AdminDao {
	
	Admin findById(int id);
	
	List<Admin> findAll();
	
	Admin findByCode(String code);
	
	void update(Admin admin);
	
	int save(Admin admin);
	
	void delete(int id);

}
