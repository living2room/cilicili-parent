package com.cilicili.user.controller.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return "Login";
	}

	/*
	 * 登陆逻辑处理
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String , Object> login(String email, String userPassword, @RequestParam(value="isRememberMe", defaultValue="0") int isRememberMe,Model model) {

		System.out.println("asa"+isRememberMe);
		Map<String,Object> map = new HashMap<>();
		/*
		 * 使用Shiro编写认证操作
		 */
		// 获取subject
		Subject currentUser = SecurityUtils.getSubject();
		Users findByEmail = this.usersServiceImpl.findByEmail(email);
		String salt = "";
		
		if(findByEmail != null) {
			salt = findByEmail.getSalt();
		}else {
			map.put("aa",0);
			return map;
		}
		// Session session = currentUser.getSession();
		// session.setAttribute("someKey", "aValue");
		if (!currentUser.isAuthenticated()) {			
			//将输入的密码加密
			String Md5Password = new Md5Hash(userPassword, salt, 3).toString(); // 生成的密文
			// 封装用户数据
			JudgeUsernamePasswordToken token = new JudgeUsernamePasswordToken(email, Md5Password,
					LoginType.USER.toString());
			//记住我
			if (isRememberMe == 1) {
	            token.setRememberMe(true);
	        }
			
			
			
			// 执行登陆方法
			try {
 				currentUser.login(token);
				/*
				 * 把user用户数据通过Session.put放在session值栈中。SysConstant.CURRENT_USER_INFO：是一个返回的值，
				 * 在jsp页面中可以接收到，也可以直接写一个字符串让页面接收，返回的数据可以在页面做回显等功能
				 */

				// 当认证成功后将shiro中保存的用户对象去出来，全部对象信息
				Users user = (Users) currentUser.getPrincipal();

				Session session = currentUser.getSession();
				session.setAttribute("user", user);
				session.setAttribute("userName", user.getUserName());

				// 登陆成功!
				// 跳转到test页面
				// model.addAttribute("role", token.getUsername());
				map.put("aa", 1);
				return map;

			} catch (UnknownAccountException uae) {

				model.addAttribute("msg", "用户名不存在");
				map.put("aa", 0);
				return map;
			} catch (IncorrectCredentialsException ice) {
				model.addAttribute("msg", "密码错误");
				map.put("aa", 0);
				return map;
			} catch (LockedAccountException lae) {
				model.addAttribute("msg", "用户被禁用");
				map.put("aa", -1);
				return map;
			} catch (AuthenticationException ae) {
				model.addAttribute("msg", "其他错误");
				map.put("aa", 0);
				ae.printStackTrace();
				return map;
			}

		}
		map.put("aa", 1);
		return map;
	}

	//退出
	@RequestMapping("loginAction_logout")

	public String logout(HttpServletRequest request) {
		// 获取subject
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.removeAttribute("user");// 删除session
		SecurityUtils.getSubject().logout();// 调用登出方法
        System.out.println("退出登录！");
		return "kk";
	}

	
	
	@RequestMapping("/toRegister")
	public String toRegister() {
		return "registered";
	}

	/*
	 * 注册逻辑处理
	 */
	@RequestMapping(value = "/register", produces = "application/json")
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
			String uuid = UUID.randomUUID().toString();
			String userId = uuid.replace("-", ""); 
			user.setUserId(userId);
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
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
