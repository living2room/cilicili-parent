package com.cilicili.user.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.user.shiro.ultra.JudgeUsernamePasswordToken;
import com.cilicili.user.shiro.ultra.LoginType;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "adminlogin";
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
//		Session session = currentUser.getSession();
		//session.setAttribute("someKey", "aValue");
		if (!currentUser.isAuthenticated()) { 

			System.out.println("1"+userName);
			System.out.println("2"+userPassword);
			// 封装用户数据
			JudgeUsernamePasswordToken token = new JudgeUsernamePasswordToken(userName, userPassword, LoginType.ADMIN.toString());
			token.setRememberMe(true);
			// 执行登陆方法
			try {
				currentUser.login(token);
				
				
				/* 在页面shiro标签的使用 */
				//model.addAttribute("adminName", token.getUsername());
				
				// 登陆成功！
				// 跳转到test页面
//				return "redirect:/admin/background";
				return "background";

			} catch (UnknownAccountException uae) {
				model.addAttribute("msg", "用户名不存在");
				return "adminlogin";
			} catch (IncorrectCredentialsException ice) {
				model.addAttribute("msg", "密码错误");
				return "adminlogin";
			} catch (LockedAccountException lae) {
				model.addAttribute("msg", "用户名被禁用");
				return "adminlogin";
			} catch (AuthenticationException ae) {
				model.addAttribute("msg", "其他错误");
				ae.printStackTrace();
				return "adminlogin";
			}

		}
		return "background";
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
	@RequestMapping("/background")
	public String background() {
		return "background";
	}
}
