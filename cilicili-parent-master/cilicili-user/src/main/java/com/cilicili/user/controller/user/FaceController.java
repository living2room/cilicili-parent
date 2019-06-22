package com.cilicili.user.controller.user;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cilicili.user.domain.user.Users;
import com.cilicili.user.service.impl.user.UsersServiceImpl;


/**
 * 页面跳转
 * 
 *
 */
@Controller
@RequestMapping("/")
public class FaceController {
	
	@Resource
	private UsersServiceImpl usersServiceImpl;
	
	
	//人脸模块对象，人脸登录
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "face";
	}
	
	//注册人脸
	@RequestMapping(value="/storage",method=RequestMethod.GET)
	public String storage(){
		return "storage";
	}
	
/*	1231321
	login(String email, String userPassword,
			@RequestParam(value = "isRememberMe", defaultValue = "0") int isRememberMe, HttpServletRequest request,
			HttpServletResponse response, Model model)*/
	
	//人脸登录
	@RequestMapping(value="/faceLogin")
	public String faceLogin(String userName,HttpServletRequest request, Model model){
		
		
		
		Users findByEmail = this.usersServiceImpl.findByUserName(userName);
		System.out.println("oo"+userName);
		findByEmail.getUserPassword();

			// 获取当前用户的IP地址
			try {
				InetAddress addr = InetAddress.getLocalHost();
				String ip = addr.getHostAddress().toString(); // 获取本机ip

				HttpSession session = request.getSession();
				session.setAttribute("user", findByEmail);
				session.setAttribute("userName", findByEmail.getUserName());

				// ServletContext application=this.getServletContext();
				// 为了踢掉旧用户，需要拦截器的相关配置
				/*
				 * HttpSession session1 = request.getSession(); ServletContext application =
				 * session1.getServletContext(); application.setAttribute("nowuser", user+ip);
				 */

				// System.out.println(application.getAttribute("nowuser")+"<iphhh");
				System.out.println(ip + "iphhh");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return "index";
	}
}