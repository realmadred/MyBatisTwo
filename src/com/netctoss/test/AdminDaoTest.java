package com.netctoss.test;

import java.sql.Timestamp;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netctoss.dao.AdminDao;
import com.netctoss.entity.Admin;

public class AdminDaoTest {

	@Test
	public void testFindAll(){
		@SuppressWarnings("resource")
		ApplicationContext aContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AdminDao adminDao = aContext.getBean("adminDao",AdminDao.class);
		//查询
		System.out.println(adminDao.findAll());
	}
	
	@Test
	public void testFindById(){
		@SuppressWarnings("resource")
		ApplicationContext aContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AdminDao adminDao = aContext.getBean("adminDao",AdminDao.class);
		//查询
		System.out.println(adminDao.findById(2000));
	}
	
	@Test
	public void testFindByCode(){
		@SuppressWarnings("resource")
		ApplicationContext aContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AdminDao adminDao = aContext.getBean("adminDao",AdminDao.class);
		//查询
		System.out.println(adminDao.findByCode("admin"));
	}
	
	@Test
	public void testSave(){
		@SuppressWarnings("resource")
		ApplicationContext aContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AdminDao adminDao = aContext.getBean("adminDao",AdminDao.class);
		//保存
		Admin admin = new Admin();
		admin.setAdmin_id(8000);
		admin.setAdmin_code("feng");
		admin.setEmail("www.163.con");
		admin.setName("峰");
		admin.setPassword("111111");
		admin.setTelephone("13899999999");
		admin.setEnrolldate(Timestamp.valueOf("2016-03-04 22:22:22"));
		adminDao.save(admin);
	}
	
	@Test
	public void testUpdate(){
		@SuppressWarnings("resource")
		ApplicationContext aContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AdminDao adminDao = aContext.getBean("adminDao",AdminDao.class);
		//保存
		Admin admin = new Admin();
		admin.setAdmin_id(8000);
		admin.setName("峰云");
		admin.setPassword("111111");
		admin.setEnrolldate(Timestamp.valueOf("2016-03-04 22:22:22"));
		adminDao.update(admin);
	}
	
	@Test
	public void testDelete(){
		@SuppressWarnings("resource")
		ApplicationContext aContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AdminDao adminDao = aContext.getBean("adminDao",AdminDao.class);
		//保存
		adminDao.delete(8000);
	}
}
