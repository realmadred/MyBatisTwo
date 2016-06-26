package com.netctoss.dao;

import java.util.List;

import com.netctoss.entity.AdminRole;

/**
 * 管理员dao
 * @author Madrid
 *
 */
public interface AdminRoleDao {
	
	void deleteByAdminId(int adminId);

	//添加管理员角色表
	void insertAdminRole(AdminRole adminRole);

	//批量添加管理员角色
	void saveAdminRole(List<AdminRole> adminRoles);

}
