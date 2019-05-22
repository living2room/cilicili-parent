package com.cilicili.user.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cilicili.user.domain.user.Users;
import com.cilicili.user.service.impl.user.UsersServiceImpl;
import com.cilicili.user.shiro.ultra.JudgeUsernamePasswordToken;
import com.cilicili.user.shiro.ultra.LoginType;
@Controller
@RequestMapping("/user")
public class UsersController {

	@Resource
	private UsersServiceImpl usersServiceImpl;

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "userlogin";
	}

	/*
	 * 登陆逻辑处理
	 */
	@RequestMapping("/login")
	public String login(String userName, String userPassword, Model model) {

		/*
		 * 使用Shiro编写认证操作
		 */
		// 获取subject
		Subject currentUser = SecurityUtils.getSubject();

		// Session session = currentUser.getSession();
		// session.setAttribute("someKey", "aValue");
		if (!currentUser.isAuthenticated()) {

			System.out.println("1" + userName);
			System.out.println("2" + userPassword);
			// 封装用户数据
			JudgeUsernamePasswordToken token = new JudgeUsernamePasswordToken(userName, userPassword,
					LoginType.USER.toString());
			token.setRememberMe(true);
			// 执行登陆方法
			try {
				currentUser.login(token);
				// 登陆成功！
				// 跳转到test页面

				// model.addAttribute("role", token.getUsername());
				return "kk";

			} catch (UnknownAccountException uae) {

				model.addAttribute("msg", "用户名不存在");
				return "userlogin";
			} catch (IncorrectCredentialsException ice) {
				model.addAttribute("msg", "密码错误");
				return "userlogin";
			} catch (LockedAccountException lae) {
				model.addAttribute("msg", "用户名被禁用");
				return "userlogin";
			} catch (AuthenticationException ae) {
				model.addAttribute("msg", "其他错误");
				ae.printStackTrace();
				return "userlogin";
			}

		}
		return "kk";
	}

	@RequestMapping("/toRegister")
	public String toRegister() {
		return "register";
	}

	/*
	 * 注册逻辑处理
	 */
	@RequestMapping(value ="/register", produces="application/json")
	@ResponseBody
	public Map<String, Object> register(Users user, Model model) {
		
		Map<String, Object> hMap = new HashMap<String, Object>();
		
		String userName = user.getUserName(); 
		String email = user.getEmail();
		String userPassword = user.getUserPassword();

		String message;
		System.out.println(userName);
		System.out.println(email);
		System.out.println("123");

		Users users1 = this.usersServiceImpl.findByUserName(userName);
		Users users2 = this.usersServiceImpl.findByEmail(email);
		System.out.println("老子进来了？");
		if (users1 != null) {
			System.out.println("8080");
			message = "0";
			// String message1 = JSON.toJSONString(message);
			hMap.put("msg", message);
			return hMap;
		}
		if (users2 != null) {
			System.out.println("8080");
			message = "-1";
			// String message1 = JSON.toJSONString(message);
			hMap.put("msg", message);
			return hMap;
		}

		else if (users1 == null & users2 == null) {

			/* String userPassword = user.getUserPassword(); */
			String salt = new SecureRandomNumberGenerator().nextBytes().toHex(); // 生成盐值
			String Md5Password = new Md5Hash(userPassword, salt, 3).toString(); // 生成的密文

			/* Users user3 = new Users(); */
			user.setUserName(userName);
			user.setUserPassword(Md5Password);
			user.setEmail(email);
			user.setSalt(salt);
			 
			this.usersServiceImpl.addUser(user);
			hMap.put("msg", "1");
			return hMap;
		}
		return hMap;
		/*
		 * return "用户已存在";
		 */
	}
	
	@RequestMapping("/testThymeleaf")
	public String testThymeleaf(Model model) {
		model.addAttribute("user", "123");
		return "test";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("user", "123");
		return "add";
	}

	@RequestMapping("/update")
	public String update(Model model) {
		model.addAttribute("user", "123");
		return "update";
	}

	@RequestMapping("/noAuth")
	public String noAuth() {
		return "noAuth";
	}

	@RequestMapping("/userlogin")
	public String background() {
		return "userlogin";
	}
}
