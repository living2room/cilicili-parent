package com.cilicili.user.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cilicili.user.domain.user.Permission;
import com.cilicili.user.domain.user.Role;
import com.cilicili.user.domain.user.Users;
import com.cilicili.user.service.impl.user.UsersServiceImpl;
import com.cilicili.user.shiro.ultra.JudgeUsernamePasswordToken;
import com.cilicili.user.shiro.ultra.LoginType;

public class UsersRealm extends AuthorizingRealm {

	
	
	@Autowired
	private UsersServiceImpl usersService;

	
	/*
	 * @Override public String getName() { return LoginType.USER.toString(); }
	 */
	
	/**
	 * 授权执行逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {

		//校验当前用户类型是否正确，正确则进入处理角色权限问题，否则跳出
		//arg0.getRealmNames()是下方的认证逻辑（登录）传上来的
		if (!arg0.getRealmNames().contains("usersRealm")) return null; 
		
        System.out.println("User授权执行逻辑");
		
		
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
		
		//sdgsdg
		//bug出在这里
		Users user = (Users) subject.getPrincipal();
		// String perms = userService.findByUserName(user.getUsername()).getPerms();
		// info.addStringPermission(perms);
		/*
		 * ============================================================================
		 */
		
		//角色
		/*
		 * Users user3 = this.usersService.findById(user.getUserId()); List<Role>
		 * roleList= user3.getRoleList(); for (Role role : roleList) { List<Permission>
		 * permissionList = role.getPermissionList(); for (Permission permission :
		 * permissionList) { permission.getPermissionName(); permission.getUrl();
		 * System.out.println("地址url："+permission.getUrl()); } String roleName =
		 * role.getRoleName(); System.out.println("拥有的角色有："+roleName);
		 * info.addRole(roleName); }
		 */
		
		
		
		//String role = user.getUserName();

		//System.out.println("哈哈" + user);
		//System.out.println("哈哈role：" + role);

		//info.addRole(role);
		// info.addRoles(roles);
		// info.addStringPermissions(permissions);
		// clearCachedAuthorizationInfo();

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
System.out.println("走了这里");
		return info;

	}

	/**
	 * 执行认证逻辑（登录）
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("BBBBB执行认证逻辑");

		// 编写shiro的判断逻辑，判断用户名和密码

		// 判断用户名
		JudgeUsernamePasswordToken jToken = (JudgeUsernamePasswordToken) token;

		
		//if (!jToken.getLoginType().contains(getName())) return null;
		
		
		/*
		 * 不需要 //通过utoken 获取用户密码，并转换成String类型，注意这里的转换不能强转，要用new的方法 String userPassword =
		 * new String(jToken.getPassword());
		 */
		// 假设数据库的用户名和密码

		// userName获取到的是email
		String email = new String(jToken.getUsername());
		System.out.println("11" + email);

		/*
		 * if(用户和管理员分开)
		 * 
		 * 
		 */
		Users user = this.usersService.findByEmail(email);
		System.out.println("11" + email);
		System.out.println("21" + user.getEmail());
		System.out.println("22" + user.getUserPassword());
		System.out.println("user.getStatus()::"+user.getStatus());
		String realmName = getName();
		System.out.println("user.getStatus()2getName():2:"+realmName);
		
		if (user == null) {
			// 该用户不存在
			return null;// shiro底层会抛出一个UnknownAccountException
		} /*
			 * else if (user.getStatus() == 1) { System.out.println(user.getStatus()); throw
			 * new LockedAccountException(); }
			 */
		 

		// throw new LockedAccountException("用户被锁定"); }

		// return new SimpleAuthenticationInfo(user, user.getPassword(), getName()) ;
		// 这里如果上面的都成立后会return到密码校验哪里去
		/*
		 * SimpleAuthenticationInfo:是doGetAuthenticationInfo的一个实现类，因为
		 * doGetAuthenticationInfo 是一个接口不能直接new 把返回值添加到条件中  principal:主要对象（登录的用户） ,
		 * credentials:密码 ,realm的名字可以通过getName获取类名作为区分
		 */
		// 判断密码，user.getUserPassword()是数据库中的密码
		
		
		
		return new SimpleAuthenticationInfo(user, user.getUserPassword(),"usersRealm");

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
