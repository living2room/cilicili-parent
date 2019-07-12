package com.cilicili.user.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.domain.user.admin.AdminPermission;
import com.cilicili.domain.user.admin.AdminRole;
import com.cilicili.domain.user.admin.AdminUser;
import com.cilicili.domain.user.user.Users;
import com.cilicili.user.service.impl.admin.AdminPermissionServiceImpl;
import com.cilicili.user.service.impl.admin.AdminRoleServiceImpl;
import com.cilicili.user.service.impl.admin.AdminUserServiceImpl;
import com.cilicili.user.service.impl.user.UsersServiceImpl;
import com.cilicili.user.shiro.ultra.JudgeUsernamePasswordToken;
import com.cilicili.user.shiro.ultra.LoginType;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource
	private AdminUserServiceImpl adminUserServiceImpl;
	@Resource
	private UsersServiceImpl usersServiceImpl;
	@Resource
	private AdminRoleServiceImpl adminRoleServiceImpl;
	@Resource
	private AdminPermissionServiceImpl adminPermissionServiceImpl;

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "user/adminlogin";
	}

	/*
	 * 登陆逻辑处理
	 */
	@RequestMapping("/login")
	public String login(String userName, String userPassword, Model model) {
System.out.println("????????????/");
		/*
		 * 使用Shiro编写认证操作
		 */
		// List<String> urlList = new ArrayList<String>();
		// List<String> descriptionList = new ArrayList<String>();
		List<AdminPermission> adminPermissionList3 = new ArrayList<AdminPermission>();
		List<AdminPermission> seconadminPermission11 = new ArrayList<>();
		List<AdminPermission> oneList = new ArrayList<>();
		// 获取subject
		Subject currentUser = SecurityUtils.getSubject();
//		Session session = currentUser.getSession();
		// session.setAttribute("someKey", "aValue");
		
		//if (!currentUser.isAuthenticated()) {

			System.out.println("1" + userName);
			System.out.println("2" + userPassword);
			// 封装用户数据
			JudgeUsernamePasswordToken token = new JudgeUsernamePasswordToken(userName, userPassword,
					LoginType.ADMIN.toString());

			System.out.println("CONTROLLER的" + LoginType.ADMIN.toString());
			// token.setRememberMe(true);
			// 执行登陆方法
			try {
				currentUser.login(token);

				AdminUser adminUser1 = this.adminUserServiceImpl.findByUserName(userName);
				int userId = adminUser1.getUserId();

				/* 查一个用户下的所有角色及每个角色下的所有权限 */
				// AdminUser adminUser = this.adminUserServiceImpl.all(userId);
				// adminUser.
				/*
				 * 通过userId 查找管理员的所有角色的信息，多表连接
				 */

				AdminUser adminUser = this.adminUserServiceImpl.all(userId);

				// 拥有的所有权限
				List<AdminRole> adminRoleList = adminUser.getAdminRoleList();
				// List<AdminPermission> adminPermissionList3 =new ArrayList<AdminPermission>();
				// 每个账号拥有的角色集合
				for (AdminRole adminRole : adminRoleList) {
					List<AdminPermission> adminPermissionList = adminRole.getAdminPermission();
					// 所有拥有的权限的集合条数
					for (AdminPermission adminPermissionList2 : adminPermissionList) {
						// 多条一级菜单的记录的List集合
						if (adminPermissionList2.getParentId() == 0) {
							adminPermissionList3.add(adminPermissionList2);
							// 二级菜单的集合
						}
					}
				}

				for (AdminPermission adminPermission : adminPermissionList3) {

					int parentId = adminPermission.getPermissionId();
					List<AdminPermission> adminPermissionList22 = this.adminPermissionServiceImpl.Two(parentId);
					adminPermission.setAdminPermission(adminPermissionList22);
				}

				/*
				 * // 把二级菜单放进一级菜单 for (AdminPermission seconadminPermission :
				 * seconadminPermission11) {
				 * 
				 * }
				 */

				System.out.println("最新权限哈哈哈1" + adminPermissionList3);

				// 权限，菜单
				Session session = currentUser.getSession();
				session.setAttribute("adminPermissionList3", adminPermissionList3);
				
				//用户名
				session.setAttribute("adminName",adminUser.getUserName());

				
				/*
				 * AdminUser adminUser2 = this.adminUserServiceImpl.all(userId); List<AdminRole>
				 * adminRoleList = adminUser2.getAdminRoleList(); //List<AdminPermission>
				 * adminPermission = new ArrayList<AdminPermission>();
				 * 
				 * // 每个账号拥有的角色集合 for (AdminRole adminRole : adminRoleList) {
				 * 
				 * // adminRole.getAdminPermission(); // int roleId = adminRole.getRoleId(); //
				 * 每个角色拥有的权限集合 List<AdminPermission> adminPermissionList =
				 * adminRole.getAdminPermission(); for (AdminPermission adminPermissionList2 :
				 * adminPermissionList) { //多条AdminPermission记录的List集合
				 * adminPermissionList3.add(adminPermissionList2);
				 * //adminPermissionList2.getUrl() }
				 * 
				 * } System.out.println("adminPermissionList321:" + adminPermissionList3);
				 * 
				 * // 权限，菜单 Session session = currentUser.getSession();
				 * session.setAttribute("adminPermissionList3", adminPermissionList3);
				 */

				// return "user/superIndex";
				return "redirect:/to/b/index";

			} catch (UnknownAccountException uae) {
				model.addAttribute("msg", "用户名或密码错误");
				return "user/adminlogin";
			} catch (IncorrectCredentialsException ice) {
				model.addAttribute("msg", "用户名或密码错误");
				return "user/adminlogin";
			} catch (LockedAccountException lae) {
				model.addAttribute("msg", "用户名或密码错误");
				return "user/adminlogin";
			} catch (AuthenticationException ae) {
				model.addAttribute("msg", "用户名或密码错误");
				//ae.printStackTrace();
				return "user/adminlogin";
			}

		//}
		//return "redirect:/to/b/index";

	}
	@RequestMapping("logout")
	public String logout() {
		// 获取subject
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.removeAttribute("adminName");
		session.removeAttribute("adminPermissionList3");
		SecurityUtils.getSubject().logout();// 调用登出方法
		return "/admin/toLogin";
	}

	@RequestMapping("/background")
	public String background() {
		return "user/background";
	}

	@RequestMapping("/toTable")
	public String toTable() {
		return "redirect:/admin/table";
	}

	@RequestMapping("/table")
	public String table(Model model) {

		List<Users> usersList = usersServiceImpl.findAll();
		model.addAttribute("usersList", usersList);
		return "user/table_data_tables";
	}

	// 禁用用户
	@RequestMapping("/lock")
	public String lock(Model model, String userName) {
		int status = 2;
		int user = usersServiceImpl.disable(userName, status);
		return "redirect:/admin/table";
	}

	// 启用用户
	@RequestMapping("/unlock")
	public String unlock(Model model, String userName) {
		int status = 1;
		int user = usersServiceImpl.disable(userName, status);
		return "redirect:/admin/table";
	}

}
