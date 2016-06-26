package com.netctoss.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netctoss.dao.AdminRoleDao;
import com.netctoss.entity.AdminRole;

public class AdminRoleDaoTest {
	@Test
	public void testSave(){
		@SuppressWarnings("resource")
		ApplicationContext aContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AdminRoleDao adminRoleDao = aContext.getBean("adminRoleDao",AdminRoleDao.class);
		//保存
		List<AdminRole> adminRoles = new ArrayList<>();
		AdminRole adminRole = new AdminRole();
		adminRole.setAdmin_id(9000);
		adminRole.setRole_id(100);
		adminRoles.add(adminRole);
		AdminRole adminRole1 = new AdminRole();
		adminRole1.setAdmin_id(9000);
		adminRole1.setRole_id(200);
		adminRoles.add(adminRole1);
		//保存数据
		adminRoleDao.saveAdminRole(adminRoles);
	}
	
}
