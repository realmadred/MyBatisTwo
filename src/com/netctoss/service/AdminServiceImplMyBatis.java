package com.netctoss.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.netctoss.constans.NetctossConstans;
import com.netctoss.dao.AdminDao;
import com.netctoss.dao.AdminRoleDao;
import com.netctoss.dao.RoleDao;
import com.netctoss.entity.Admin;
import com.netctoss.entity.AdminRole;
import com.netctoss.entity.NetctossResult;
import com.netctoss.entity.Role;

@Service // 加入到容器
public class AdminServiceImplMyBatis implements AdminServcie {

	@Resource // 注入dao
	private AdminDao adminDao;
	@Resource // 注入
	private RoleDao roleDao;
	@Resource // 注入
	private AdminRoleDao adminRoleDao;

	@Override
	public NetctossResult login(String username, String password, String imageCode, HttpSession session) {
		NetctossResult result = new NetctossResult();

		// 检查用户名和密码不能为空
		if (null == username || "".equals(username)) {
			result.setState(1);// 验证不通过
			result.setMsg("用户名不能为空");
			return result;
		}
		if (null == password || "".equals(password)) {
			result.setState(1);// 验证不通过
			result.setMsg("密码不能为空");
			return result;
		}
		if (null == imageCode || "".equals(imageCode)) {
			result.setState(1);// 验证不通过
			result.setMsg("验证码不能为空");
			return result;
		}

		// 检查验证码
		Object attribute = session.getAttribute(NetctossConstans.IMAGECODE);
		if (attribute == null || !imageCode.equals(attribute.toString())) {
			result.setState(1);// 验证不通过
			result.setMsg("验证码正确");
			return result;
		}
		// 根据查询用户是否存在
		Admin admin = adminDao.findByCode(username.trim());
		// 检查用户名是否存在
		if (null == admin) {
			result.setState(1);// 验证不通过
			result.setMsg("用户名不存在");
			return result;
		}
		// 检验密码
		if (!password.equals(admin.getPassword())) {
			result.setState(1);// 验证不通过
			result.setMsg("密码不正确");
			return result;
		}
		// 校验通过
		result.setState(0);// 验证通过
		//设置session
		session.setAttribute(NetctossConstans.ISLOGIN, username);
		return result;
	}

	@Override
	public NetctossResult checkName(String username) {
		NetctossResult result = new NetctossResult();
		// 检查用户名不能为空
		if (null == username || "".equals(username)) {
			result.setState(1);// 验证不通过
			result.setMsg("用户名不能为空");
			return result;
		}

		// 根据查询用户是否存在
		Admin admin = adminDao.findByCode(username.trim());
		// 检查用户名是否存在
		if (null == admin) {
			result.setState(1);// 验证不通过
			result.setMsg("用户名不存在");
			return result;
		}
		result.setState(0);// 验证通过
		return result;
	}

	/**
	 * 显示所有的管理员
	 */
	@Override
	public NetctossResult adminList() {
		NetctossResult result = new NetctossResult();
		List<Admin> admins = adminDao.findAll();
		result.setState(0);
		result.setData(admins);
		return result;
	}

	@Override
	public NetctossResult getAdmin(String id) {
		NetctossResult result = new NetctossResult();
		if (id == null) {
			result.setState(1);
			result.setMsg("id为空！");
			return result;
		}
		Admin admin = adminDao.findById(Integer.valueOf(id));
		// 查询所有的角色
		List<Role> roles = roleDao.findAll();

		result.setState(0);
		result.setData(admin);
		result.setData2(roles);
		return result;
	}

	@Override
	public NetctossResult updateAdmin(HttpServletRequest request) {
		NetctossResult result = new NetctossResult();

		// 获取请求参数
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String admin_code = request.getParameter("admin_code");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String rolesStr = request.getParameter("roles");

		// 处理角色
		String[] strs = rolesStr.split(",");
		// System.out.println("-----------------");
		// System.out.println(Arrays.toString(strs));
		Set<Integer> roles = StringArrToIntSet(strs);
		// System.out.println(id);
		// System.out.println("-------------");
		// 创建管理员
		Admin admin = new Admin();
		// 设置属性
		admin.setAdmin_code(admin_code);
		admin.setAdmin_id(id);
		admin.setEmail(email);
		admin.setName(name);
		admin.setTelephone(phone);

		// 更新管理员
		adminDao.update(admin);

		// 删除管理员角色信息
		adminRoleDao.deleteByAdminId(id);

		// 创建adminRole
		AdminRole adminRole = new AdminRole();
		// 添加管理员角色表
		for (int roleId : roles) {
			adminRole.setAdmin_id(id);
			adminRole.setRole_id(roleId);
			adminRoleDao.insertAdminRole(adminRole);
		}
		result.setState(0);
		return result;
	}

	private Set<Integer> StringArrToIntSet(String[] strs) {
		Set<Integer> set = new HashSet<>();
		if (strs == null || strs.length == 0) {
			return set;
		}
		try {
			for (int i = 0; i < strs.length; i++) {
				set.add(Integer.valueOf(strs[i]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new NumberFormatException("数字格式化异常！");
		}
		// 返回
		return set;
	}

	@Override
	public NetctossResult deleteAdmin(int id) {
		NetctossResult result = new NetctossResult();
		// 1 删除管理员表数据
		adminDao.delete(id);
		// 2删除管理员角色数据
		adminRoleDao.deleteByAdminId(id);
		result.setState(0);
		return result;
	}

	@Override
	public NetctossResult getRoles() {
		NetctossResult result = new NetctossResult();
		// 查询所有的角色
		List<Role> roles = roleDao.findAll();
		result.setState(0);
		result.setData(roles);
		return result;
	}

	@Override
	public NetctossResult addAdmin(HttpServletRequest request) {
		NetctossResult result = new NetctossResult();

		// 获取请求参数
		String name = request.getParameter("name");
		String adminCode = request.getParameter("code");
		String password = request.getParameter("pass");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String rolesStr = request.getParameter("roles");

		// 1保存管理员表数据
		Admin admin = new Admin();
		admin.setAdmin_code(adminCode);
		admin.setName(name);
		admin.setPassword(password);
		admin.setEmail(email);
		admin.setTelephone(phone);
		admin.setEnrolldate(new Timestamp(System.currentTimeMillis()));
		adminDao.save(admin);

		// 2保存管理员角色数据
		int admin_id = admin.getAdmin_id();
		// 处理角色
		String[] strs = rolesStr.split(",");
		Set<Integer> roles = StringArrToIntSet(strs);
		Set<AdminRole> adminRoles = new HashSet<>();
		// 将角色id包装到管理员角色对象里面
		intSetToAdminRoleSet(admin_id, roles, adminRoles);

		List<AdminRole> adminRoles2 = new ArrayList<>(adminRoles);

		// 保存数据
		adminRoleDao.saveAdminRole(adminRoles2);
		result.setState(0);
		return result;
	}

	private void intSetToAdminRoleSet(int admin_id, Set<Integer> roles, Set<AdminRole> adminRoles) {
		for (int role_id : roles) {
			// 创建adminRole
			AdminRole adminRole = new AdminRole();
			adminRole.setAdmin_id(admin_id);
			adminRole.setRole_id(role_id);
			// 添加到集合
			adminRoles.add(adminRole);
		}
	}

	@Override
	public NetctossResult checkImageCode(String imageCode, HttpServletRequest req) {
		NetctossResult result = new NetctossResult();
		// 检查
		if (StringUtils.isNullOrEmpty(imageCode)) {
			result.setState(1);
			return result;
		}
		// 获取session的验证码
		Object attribute = req.getSession().getAttribute(NetctossConstans.IMAGECODE);
		String code = attribute == null ? "" : attribute.toString();
		if (!imageCode.equals(code)) {
			result.setState(1);
			result.setMsg("验证码不正确！");
			return result;
		}
		result.setState(0);
		return result;
	}

	/**
	 * @param 
	 * 	true 用户名可以使用
	 *  false 用户名不可以使用
	 */
	@Override
	public boolean checkNameExsis(String username) {
		// 检查用户名不能为空
		if (null == username || "".equals(username)) {
			return false;
		}
		// 根据查询用户是否存在
		Admin admin = adminDao.findByCode(username.trim());
		// 检查用户名是否存在
		if (null == admin) {
			return true;//可以使用
		}
		return false;//用户名已经存在不可用使用
	}
}
