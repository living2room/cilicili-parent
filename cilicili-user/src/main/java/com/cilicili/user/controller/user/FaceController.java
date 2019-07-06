package com.cilicili.user.controller.user;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cilicili.domain.user.user.Users;
import com.cilicili.user.service.impl.user.UsersServiceImpl;


/**
 * 页面跳转
 * 
 *
 */
@Controller
@RequestMapping("/go")
public class FaceController {
	
	@Resource
	private UsersServiceImpl usersServiceImpl;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	//人脸模块对象，人脸登录
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "user/face";
	}
	
	//注册人脸
	@RequestMapping(value="/storage",method=RequestMethod.GET)
	public String storage(){
		return "user/storage";
	}
	
	//人脸登录
	@RequestMapping(value="/faceLogin")
	public String faceLogin(String userName,HttpServletRequest request, Model model){
		
		
		
		Users user = this.usersServiceImpl.findByUserName(userName);
		//System.out.println("oo"+userName);
		//findByEmail.getUserPassword();

			// 获取当前用户的IP地址
			try {
				InetAddress addr = InetAddress.getLocalHost();
				String ip = addr.getHostAddress().toString(); // 获取本机ip
				redisTemplate.opsForValue().set(user.getEmail(), ip);
				
				//给一个原始头像
				redisTemplate.opsForValue().set(userName, "../img/xixi.jpg");
				String path=redisTemplate.opsForValue().get(user.getUserName());
				
				
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setAttribute("userName", user.getUserName());
				session.setAttribute("userID", user.getUserId());
				session.setAttribute("userName", user.getUserName());
				//头像
				session.setAttribute("url1", path);
				// ServletContext application=this.getServletContext();
				// 为了踢掉旧用户，需要拦截器的相关配置
				/*
				 * HttpSession session1 = request.getSession(); ServletContext application =
				 * session1.getServletContext(); application.setAttribute("nowuser", user+ip);
				 */
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

			return "user/index";
	}
}