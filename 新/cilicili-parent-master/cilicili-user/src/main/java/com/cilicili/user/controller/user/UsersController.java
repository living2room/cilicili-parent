package com.cilicili.user.controller.user;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cilicili.domain.user.user.Users;
import com.cilicili.user.service.impl.user.UsersMessageServiceImpl;
import com.cilicili.user.service.impl.user.UsersServiceImpl;
import com.cilicili.user.shiro.ultra.JudgeUsernamePasswordToken;
import com.cilicili.user.shiro.ultra.LoginType;

@Controller
@RequestMapping("/user")
public class UsersController {

	@Resource
	private UsersServiceImpl usersServiceImpl;
	@Resource
	private UsersMessageServiceImpl usersMessageServiceImpl;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request) {
		// 判断用户是否已经记住密码 Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		Cookie[] cookies = request.getCookies();
		String token = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				switch (cookie.getName()) {
				case "usercookie":
					token = cookie.getValue();
					break;
				default:
					break;
				}
			}
			System.out.println(token + ":hh");

			if (token != null & token != "") {

				String userName = token;
				HttpSession session = request.getSession();
				session.setAttribute("userName", userName);
				Users users = this.usersServiceImpl.findByUserName(userName);
				session.setAttribute("userID", users.getUserId());
				session.setAttribute("user", users);
				
				int status = users.getStatus();
				if (status == 2) {
					return "user/Login";
				}
				// 单点的设置

				InetAddress addr;
				try {
					addr = InetAddress.getLocalHost();
					String ip = addr.getHostAddress().toString(); // 获取本机ip
					redisTemplate.opsForValue().set(users.getEmail(), ip);
					
					
					//给一个原始头像
					redisTemplate.opsForValue().set(userName, "../img/xixi.jpg");
					String path=redisTemplate.opsForValue().get(users.getUserName());
					//头像
					session.setAttribute("url1", path);
					
					return "redirect:/to/home";				
				} catch (UnknownHostException e) {
					e.printStackTrace();
					// 不能直接跳到index页面 ，因为没有走login session的值没有存 出现后面找不到session的userName
					return "user/Login";
				}
			} else {
				return "user/Login";
			}

		} else {
			System.out.println("cookies为空了");
			return "user/Login";
		}
	}

	/*
	 * ！！！！！！！！！！！！！！！！ 用户的禁用需要把cookie改 在登录的时候查status是否等于1 如果等于1 禁用 并把cookie删掉
	 * 不然记住密码时会直接跳到index
	 */

	/*
	 * 登陆逻辑处理
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(String email, String userPassword,
			@RequestParam(value = "isRememberMe", defaultValue = "0") int isRememberMe, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		System.out.println("asa" + isRememberMe);
		Map<String, Object> map = new HashMap<>();
		/*
		 * 使用Shiro编写认证操作
		 */
		// 获取subject
		Subject currentUser = SecurityUtils.getSubject();
		Users findByEmail = this.usersServiceImpl.findByEmail(email);
		String salt = "";

		if (findByEmail != null) {
			salt = findByEmail.getSalt();

			// 禁用用户逻辑
			if (findByEmail.getStatus() == 2) {
				map.put("aa", -1);
				return map;
			}

		} else {
			map.put("aa", 0);
			return map;
		}
		if (!currentUser.isAuthenticated()) {

			// 是否认证通过
			// isAuthenticated = subject.isAuthenticated();
			// 将输入的密码加密
			String Md5Password = new Md5Hash(userPassword, salt, 3).toString(); // 生成的密文
			// 封装用户数据
			JudgeUsernamePasswordToken token = new JudgeUsernamePasswordToken(email, Md5Password,
					LoginType.USER.toString());
			// 执行登陆方法
			try {
				currentUser.login(token);
				/*
				 * 把user用户数据通过Session.put放在session值栈中。SysConstant.CURRENT_USER_INFO：是一个返回的值，
				 * 在jsp页面中可以接收到，也可以直接写一个字符串让页面接收，返回的数据可以在页面做回显等功能
				 */
				// 当认证成功后将shiro中保存的用户对象去出来，全部对象信息
				// Users user = (Users) currentUser.getPrincipal();
				Users user = this.usersServiceImpl.findByEmail(email);

				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				if (isRememberMe == 1) {
					Cookie cookie = new Cookie("usercookie", user.getUserName());
					cookie.setMaxAge(259200);
					// 设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
					cookie.setPath("/");
					response.addCookie(cookie);
				}

				// 获取当前用户的IP地址
				try {
					InetAddress addr = InetAddress.getLocalHost();
					String ip = addr.getHostAddress().toString(); // 获取本机ip
					// 单点的设置
					redisTemplate.opsForValue().set(user.getEmail(), ip);
					
					
					//给一个原始头像
					redisTemplate.opsForValue().set(user.getUserName(), "../img/xixi.jpg");
					String path=redisTemplate.opsForValue().get(user.getUserName());
					
					
					String userName22 = redisTemplate.opsForValue().get(user.getEmail());
					System.out.println("redisde:" + userName22);	
					Session session = currentUser.getSession();
					session.setAttribute("user", user);
					session.setAttribute("userName", user.getUserName());
					session.setAttribute("userID", user.getUserId());
					
					//头像
					session.setAttribute("url1", path);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				// 登陆成功!
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
				//ae.printStackTrace();
				return map;
			}
		}
		map.put("aa", 1);
		return map;
	}
	// 退出
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response) {
		// 获取subject
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.removeAttribute("user");// 删除session
		session.removeAttribute("userName");// 删除session
		session.removeAttribute("userID");// 删除session
		// 删除cookie，让它过期
		Cookie cookie = new Cookie("usercookie", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		SecurityUtils.getSubject().logout();// 调用登出方法
		System.out.println("退出登录！");
		return "redirect:/user/index";
	}
	@RequestMapping("/toRegister")
	public String toRegister() {
		return "user/registered";
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
		int status = user.getStatus();
		String message;
		Users users1 = this.usersServiceImpl.findByUserName(userName);
		Users users2 = this.usersServiceImpl.findByEmail(email);
		if (users1 != null) {
			message = "0";
			hMap.put("msg", message);
			return hMap;
		}
		if (users2 != null) {
			message = "-1";
			hMap.put("msg", message);
			return hMap;
		}
		else if (users1 == null & users2 == null) {
			String salt = new SecureRandomNumberGenerator().nextBytes().toHex(); // 生成盐值
			String Md5Password = new Md5Hash(userPassword, salt, 3).toString(); // 生成的密文
			String uuid = UUID.randomUUID().toString();
			String userId = uuid.replace("-", "");
			user.setUserId(userId);
			user.setUserName(userName);
			user.setUserPassword(Md5Password);
			user.setEmail(email);
			user.setSalt(salt);
			user.setStatus(status);
			this.usersServiceImpl.addUser(user);
			// 往messge里面加数据
			this.usersMessageServiceImpl.addMessage(userId, userName);
			hMap.put("msg", "1");
			return hMap;
		}
		return hMap;
	}
	@RequestMapping("/toUpPassword")
	public String toUpPassword() {
		return "user/upPassword";
	}
	// 修改密码
	@RequestMapping("/upPassword")
	@ResponseBody
	public Map<String, Object> upPassword(String userName, String email, String userPassword, String newPassword) {
		System.out.println("88:" + userName);
		Users users = this.usersServiceImpl.findByUserName(userName);
		Map<String, Object> map = new HashMap<>();
		if (users != null) {
			String salt = users.getSalt();
			// 对输入的密码进行加密后做比较
			String Md5Password = new Md5Hash(userPassword, salt, 3).toString(); // 生成的密文
			String dbPassword = users.getUserPassword();
			if (Md5Password.equals(dbPassword)) {
				// 更改数据库的密码
				String newMd5Password = new Md5Hash(newPassword, salt, 3).toString(); // 生成的密文
				this.usersServiceImpl.upPassword(userName, newMd5Password);
				map.put("msg", 1);
			} else {
				map.put("msg", 0);
			}
		} else {
			map.put("msg", 0);
		}
		return map;
	}
	// 忘记密码
	@RequestMapping("/toUnknown")
	public String toUnknown() {
		return "user/unknownPassword";
	}
	// 查数据库里面是否有这个邮箱的注册
	@RequestMapping("/verification")
	@ResponseBody
	public Map<String, Object> verification(String email, Model model) {
		Users users = this.usersServiceImpl.findByEmail(email);
		Map<String, Object> map = new HashMap<String, Object>();
		if (users != null) {
			// 存在该邮箱，可以进行下一步
			map.put("msg", 1);
		} else {
			map.put("msg", 0);
		}
		// 还要查数据库里面是否有这个邮箱的注册
		// model.addAttribute("email", email);
		return map;
	}
	// 忘记密码,要修改密码的邮箱号
	@RequestMapping("/wangjimima")
	public String wangjimima(String email, Model model) {
		model.addAttribute("email", email);
		return "user/wangjimima";
	}
	// 新密码提交的页面
	@RequestMapping("/aa")
	@ResponseBody
	public Map<String, Object> wangjimima(String email, String userPassword) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users users = this.usersServiceImpl.findByEmail(email);
		if (users != null) {
			String salt = users.getSalt();
			String userName = users.getUserName();
			// 更改数据库的密码
			try {
				String newMd5Password = new Md5Hash(userPassword, salt, 3).toString(); // 生成的密文
				this.usersServiceImpl.upPassword(userName, newMd5Password);
				map.put("msg", 1);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("msg", 0);
			}
		} else {
			map.put("msg", -1);
		}

		return map;
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("user", "123");
		return "user/face";
	}

	@RequestMapping("/update")
	public String update(Model model) {
		model.addAttribute("user", "123");
		return "user/Login";
	}

	@RequestMapping("/noAuth")
	public String noAuth() {
		return "user/noAuth";
	}

	@RequestMapping("/userlogin")
	public String background() {
		return "user/userlogin";
	}

	@RequestMapping("/index")
	public String index() {
		return "user/index";
	}

	@RequestMapping("/toPassword03")
	public String toPassword03() {
		return "user/password03";
	}

}
