
package com.cilicili.user.controller.user;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * //单点的设置的拦截器
 */
public class MyInterceptor implements HandlerInterceptor {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	// 在请求处理之前进行调用（Controller方法调用之前
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
			throws Exception {
		System.out.printf("preHandle被调用\n");
		HttpSession session = httpServletRequest.getSession();
		String email = (String) session.getAttribute("${session.user.getEmail()}");
		// Users users = this.usersServiceImpl.findByUserName(userName);
		String email1;
		String ip;
		try { // 单点的设置 
			
			email1 =	redisTemplate.opsForValue().get(email);
			InetAddress addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress().toString();
			if (email1.equals(ip)) { 

				return true;
			} else {
				// 退出登录
				Subject currentUser = SecurityUtils.getSubject();
				currentUser.logout();
				return true;
			}
		} catch (Exception e) {
			return true;
		}
	}

	// 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle被调用\n");
	}

	// 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
		System.out.println("afterCompletion被调用\n");
	}
}
