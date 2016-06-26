package com.netctoss.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.netctoss.entity.NetctossResult;

public interface AdminServcie {

	//登陆
	NetctossResult login(String username, String password, String imageCode, HttpSession session);

	//检查用户名
	NetctossResult checkName(String username);

	//显示所有的管理员
	NetctossResult adminList();

	//根据id获取管理员信息
	NetctossResult getAdmin(String id);

	//更新管理员角色表数据
	NetctossResult updateAdmin(HttpServletRequest request);

	//删除管理员
	NetctossResult deleteAdmin(int id);

	//获取所有的角色
	NetctossResult getRoles();

	//添加管理员
	NetctossResult addAdmin(HttpServletRequest request);

	//检查验证码
	NetctossResult checkImageCode(String imageCode, HttpServletRequest req);

	//检查用户名是否存在返回boolean
	boolean checkNameExsis(String username);
	
}
