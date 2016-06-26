package com.netctoss.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netctoss.constans.NetctossConstans;
import com.netctoss.entity.NetctossResult;
import com.netctoss.exception.MyException;
import com.netctoss.service.AdminServcie;
import com.netctoss.util.ImageUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Controller//控制器注解
@RequestMapping("/admin")
public class AdminController {
	
	private static final Log log = LogFactory.getLog(AdminController.class);
	
	@Resource//注入 根据类型
	private AdminServcie adminService;
	
	//去往index页面
	@RequestMapping("/toIndex.do")
	//@ResponseBody//json输出
	public String toIndex(){
		return "main/index";
	}
	
	//去往管理员列表
	@RequestMapping("/toAdminList.do")
	public String toAdminList(){
		return "admin/admin_list";
	}
	
	//去往管理员更新页面
	@RequestMapping("/toUpdate.do")
	public String toUpdate(String id){
		System.out.println(id);
		return "admin/admin_update";
	}
	
	//去往管理员增加页面
	@RequestMapping("/toAdminAdd.do")
	public String toAdminAdd(){
		return "admin/admin_add";
	}
	
	//检查用户名是否存在
	@RequestMapping("/checkName.do")
	@ResponseBody//json输出
	public NetctossResult checkName(String username){
		return adminService.checkName(username);
	}
	
	//检查用户名是否存在
	@RequestMapping("/checkNameExsis.do")
	@ResponseBody//json输出
	public boolean checkNameExsis(@RequestParam("code_input") String username){
		System.out.println(username);
		return adminService.checkNameExsis(username);
	}
	
	//检查验证码是否正确
	@RequestMapping("/checkImageCode.do")
	@ResponseBody//json输出
	public NetctossResult checkImageCode(String imageCode,HttpServletRequest req){
		return adminService.checkImageCode(imageCode,req);
	}
	
	//获取管理员数据
	@RequestMapping("/getAdmin.do")
	@ResponseBody//json输出
	public NetctossResult getAdmin(String id){
		System.out.println(id);
		return adminService.getAdmin(id);
	}
	
	//获取角色数据
	@RequestMapping("/getRoles.do")
	@ResponseBody//json输出
	public NetctossResult getRoles(){
		return adminService.getRoles();
	}
	
	//登陆
	@RequestMapping("/login.do")
	@ResponseBody//json输出
	public NetctossResult login(String username, String password, String imageCode, HttpSession session){
		log.info(username+"login...");
		return adminService.login(username,password,imageCode,session);
	}
	
	//显示列表
	@RequestMapping("/adminList.do")
	@ResponseBody//json
	public NetctossResult adminList(){
		return adminService.adminList();
	}
	
	//更新管理员
	@RequestMapping("/updateAdmin.do")
	@ResponseBody//json
	public NetctossResult updateAdmin(HttpServletRequest request){
		return adminService.updateAdmin(request);
	}
	
	//删除管理员
	@RequestMapping("/deleteAdmin.do")
	@ResponseBody//json
	public NetctossResult deleteAdmin(int id){
		log.info("deleteAdmin...");
		return adminService.deleteAdmin(id);
	}
	
	//添加管理员
	@RequestMapping("/addAdmin.do")
	@ResponseBody//json
	public NetctossResult addAdmin(HttpServletRequest request){
		return adminService.addAdmin(request);
	}
	
	//添加管理员
	@RequestMapping("/getImage.do")
	//@ResponseBody//json
	public void getImage(HttpServletRequest request,HttpServletResponse response){
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			HttpSession session = request.getSession();
			String code = ImageUtil.getImage(outputStream);
			session.setAttribute(NetctossConstans.IMAGECODE, code);
			outputStream.flush();
		} catch(Exception e){
			throw new MyException("获取验证码失败！",e);
		}finally {
			if(outputStream != null){
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
