package com.netctoss.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.netctoss.constans.NetctossConstans;

/**
 * 拦截器，实习登陆过滤
 * @author Madrid
 *
 */
public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		//获取session
		HttpSession session = req.getSession();
		//检查session里面有没有绑定登陆成功的属性
		Object isLogin = session.getAttribute(NetctossConstans.ISLOGIN);
		if(isLogin != null){
			return true;
		}
		//System.out.println(arg2.getClass().getName());
		System.out.println(arg2+"被拦截");
		String contextPath = req.getContextPath();
		res.sendRedirect(contextPath+"/login.html");//跳转到登陆页面
		return false;
	}
}
