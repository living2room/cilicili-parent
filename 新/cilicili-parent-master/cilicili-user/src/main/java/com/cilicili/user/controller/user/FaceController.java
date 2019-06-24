package com.cilicili.user.controller.user;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cilicili.domain.user.user.Users;
import com.cilicili.user.service.impl.user.UsersServiceImpl;
import com.cilicili.user.service.user.RedisService;


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
	/*
	 * @Autowired JedisPool jedisPool;
	 */
	@Autowired
	    private RedisService redisService ;
	
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
				HttpSession session = request.getSession();
				session.setAttribute("user", findByEmail);
				session.setAttribute("userName", findByEmail.getUserName());	
				//单点的设置
				InetAddress addr = InetAddress.getLocalHost();
				String ip = addr.getHostAddress().toString(); // 获取本机ip
			/*
			 * Jedis jedis = jedisPool.getResource(); jedis.set(userName,ip);
			 */
				//redisService.set(userName, ip);	 
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return "user/index";
	}
}