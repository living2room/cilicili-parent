package com.cilicili;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cilicili.user.domain.user.Users;
import com.cilicili.user.mapper.admin.AdminRoleMapper;
import com.cilicili.user.mapper.admin.AdminUserMapper;
import com.cilicili.user.mapper.user.RoleMapper;
import com.cilicili.user.mapper.user.UsersMapper;
import com.cilicili.user.mapper.user.UsersMessageMapper;
import com.cilicili.user.service.impl.admin.AdminUserServiceImpl;
import com.cilicili.user.service.impl.user.UsersMessageServiceImpl;
import com.cilicili.user.service.impl.user.UsersServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CiliciliApplicationTests {

	@Test
	public void contextLoads() {
	}
  
	@Autowired
	private AdminUserMapper userMapper;
	@Resource
	private UsersServiceImpl usersServiceImpl;
	@Autowired
	private UsersMessageServiceImpl usersMessageServiceImpl;
	@Autowired
	private UsersMessageMapper usersMessageMapper;
	@Autowired
	private AdminRoleMapper adminRoleMapper;
	@Resource
	private AdminUserServiceImpl adminUserServiceimpl;
	@Autowired
	private UsersMapper usersMapper;
	@Resource
	private RoleMapper roleMapper;
	  
	
	@Test
	public void UserSelectBy() {
		 //String userName = "admin";
		// String email = "965463454@qq.com";
		// AdminUser user = this.userMapper.findByUserName(userName);

		//Users users1 = this.usersServiceImpl.findByUserName(userName);
		// Users users2 = this.usersServiceImpl.findByEmail(email);
	    //System.out.println(users1);

		//String userName = "88";
		//System.out.println("1111111111");
		 //UsersMessage users11 = this.usersMessageMapper.findByUserName(userName);
		 //System.out.println("222"+users11);
		//UsersMessage users2 = this.usersMessageServiceImpl.findByUserName(userName);
		/*
		 * AdminUser user = userMapper.findById(2); System.out.println("xixi"+user);
		 */
		//AdminRole adminRole = adminRoleMapper.findByRoleId(2);
		//System.out.println(adminRole);
		
		//AdminUser user = adminUserServiceimpl.all(1);
		//System.out.println("ss:"+user.getAdminRoleList());
		/* System.out.println("ss:"+user.get); */
		//Users user = this.usersServiceImpl.findById("2");
		//System.out.println("ss:"+user);
		//Users user = this.usersMapper.findById("1");
		
		//Role role=this.roleMapper.findByRoleId("1");
		//System.out.println("ssq:"+role.getPermissionList());
		//Role role1 = this.roleMapper.findByRoleId("1");
		//System.out.println("ssq:"+role1);
		 
		/*
		 * try { InetAddress addr = InetAddress.getLocalHost(); String
		 * ip=addr.getHostAddress().toString(); //获取本机ip System.out.println(ip+"iphhh");
		 * } catch (UnknownHostException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		//this.usersMessageServiceImpl.addMessage("23", "法的规定");
		/*
		 * UsersMessage usersMessage = new UsersMessage();
		 * 
		 * usersMessage.setUserName("lhy"); //usersMessage.setAge(6);
		 * //usersMessage.setHome("和"); //usersMessage.setPhone("158");
		 * usersMessage.setSex(2); this.usersMessageMapper.upUsersMessage(usersMessage);
		 */
		List<Users> userl = this.usersMapper.findAll();
		for (Users users : userl) {
			System.out.println(users);
		}
	}
}
