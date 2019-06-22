package com.cilicili.user.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.cilicili.user.domain.user.Users;
import com.cilicili.user.service.impl.user.UsersServiceImpl;
import com.cilicili.user.shiro.ultra.JudgeUsernamePasswordToken;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UsersServiceImpl usersService;

	/**
	 * 授权执行逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("BBBBBB授权执行逻辑");

		// 给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		// 给资源的授权字符串
		// info.addStringPermission("user:add");
		// 到数据库查询当前用户的权限字符串
		// 获取当前用户
		Subject subject = SecurityUtils.getSubject();

		// (User) subject.getPrincipal()是从下方的执行认证逻辑
		// return new SimpleAuthenticationInfo(user, user.getUserpassword(),
		// "")中的第一个参数user
		Users user = (Users) subject.getPrincipal();
		// String perms = userService.findByUserName(user.getUsername()).getPerms();
		// info.addStringPermission(perms);
		String role = user.getUserName();

		System.out.println("哈哈" + user);
		System.out.println("哈哈role：" + role);

		info.addRole(role);
		// info.addRoles(roles);
		// info.addStringPermissions(permissions);
		//clearCachedAuthorizationInfo();

		/*
		 * //认证通过后执行授权
		 * 
		 * //=========================================== // 基于角色授权
		 * //=========================================== //hasRole方法传入的是角色标识 boolean
		 * isHasRole = subject.hasRole("role1"); System.out.println("正确角色，角色有权限--->" +
		 * isHasRole);
		 * 
		 * //传入错误的角色 boolean isHasRole2 = subject.hasRole("role12");
		 * System.out.println("错误角色，角色有权限--->" + isHasRole2);
		 * 
		 * //判断多个角色 boolean hasAllRoles =
		 * subject.hasAllRoles(Arrays.asList("role1","role2"));
		 * System.out.println("多个角色判断--->" + hasAllRoles);
		 * 
		 * //使用check方法进行授权，如果不通过，会抛出异常 // try { // subject.checkRole("role12"); // }
		 * catch (AuthorizationException e) { // // TODO 自动生成的 catch 块 //
		 * e.printStackTrace(); // }
		 * 
		 * //=========================================== // 基于资源授权
		 * //=========================================== //传入的是资源标识符，单个权限判断 boolean
		 * isPermited = subject.isPermitted("user:create:1");
		 * System.out.println("资源有权限，单个权限判断--->" + isPermited);
		 * 
		 * //传入的是资源标识符，多个权限判断 boolean isPermitedAll =
		 * subject.isPermittedAll("user:create","user:update","user:delete");
		 * System.out.println("资源有权限，多个权限判断--->" + isPermitedAll);
		 * 
		 * //资源权限也支持check，如果不通过，也会抛异常 try { subject.checkPermission("item:create"); }
		 * catch (AuthorizationException e) { // TODO 自动生成的 catch 块 e.printStackTrace();
		 * }
		 * 
		 * 
		 * subject.getPrincipal();
		 * 
		 * //method
		 */

		return info;

	}

	/**
	 * 执行认证逻辑
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("BBBBB执行认证逻辑");

		// 编写shiro的判断逻辑，判断用户名和密码

		// 判断用户名
		JudgeUsernamePasswordToken jToken = (JudgeUsernamePasswordToken) token;
		String userName = jToken.getUsername();
		// 假设数据库的用户名和密码
		System.out.println("11" + userName);

		/*
		 * if(用户和管理员分开)
		 * 
		 * 
		 */
		/* 控制用户和管理员的登录 */
		Users user = this.usersService.findByUserName(userName);
		System.out.println("11" + userName);
		System.out.println("21" + user.getUserName());
		System.out.println("22" + user.getUserPassword());
		if (user == null) {
			// 用户名不存在
			return null;// shiro底层会抛出一个UnknownAccountException
		}
		// 判断密码
		return new SimpleAuthenticationInfo(user, user.getUserPassword(), "userRealm");

	}

	/**
	 * 清理缓存权限
	 */

	/*
	 * public void clearCachedAuthorizationInfo() {
	 * this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals())
	 * ; }
	 */

}
