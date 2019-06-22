package com.cilicili.user.service.impl.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cilicili.domain.user.user.Role;
import com.cilicili.domain.user.user.Users;
import com.cilicili.user.mapper.user.RoleMapper;
import com.cilicili.user.mapper.user.UsersMapper;
import com.cilicili.user.service.user.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersMapper usersMapper;
	@Resource
	private RoleMapper roleMapper;

	@Override
	public Users findByUserName(String userName) {
		return this.usersMapper.findByUserName(userName);
	}

	public int addUser(Users user) {
		return this.usersMapper.addUser(user);
	}

	public Users findByEmail(String email) {
		return this.usersMapper.findByEmail(email);
	}

	@Override
	public Users findById(String userId) {

		/* 查一个用户下的所有信息及所有角色及每个角色下的所有权限 */
		Users user = this.usersMapper.findById(userId);
		List<Role> roleList = user.getRoleList();
		List<Role> roleList2 = new ArrayList<Role>();
		for (Role role : roleList) {
			String roleId = role.getRoleId();
			Role role1 = this.roleMapper.findByRoleId(roleId);
			roleList2.add(role1);
		}
		user.setRoleList(roleList2);
		return user;

	}
	/*
	 * 修改密码
	 */
	@Override
	public int upPassword(String userName, String userPassword) {
		
		return this.usersMapper.upPassword(userName, userPassword);
	}
	
	/*
	 * 查找所有用户（给管理员）
	 */
	@Override
	public List<Users> findAll() {
	
		return this.usersMapper.findAll();
	}
	
	/*
	 * 禁用用户（给管理员）
	 */
	@Override
	public int disable(String userName, int status) {
		
		return this.usersMapper.disable(userName, status);
	}

}
