package com.cilicili.user.service.impl.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cilicili.user.domain.admin.AdminPermission;
import com.cilicili.user.domain.admin.AdminRole;
import com.cilicili.user.domain.admin.AdminUser;
import com.cilicili.user.mapper.admin.AdminUserMapper;
import com.cilicili.user.service.admin.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserMapper adminUserMapper;
	@Resource
	private AdminRoleServiceImpl adminRoleServiceImpl;
	@Override
	public AdminUser findByUserName(String userName) {
		return this.adminUserMapper.findByUserName(userName);
	}
	/*
	 *通过userId 查找管理员的所有角色的信息，多表连接
	 */
	@Override
	public AdminUser findById(int userId) {
		return this.adminUserMapper.findById(userId);
	}
	
	/* 查一个用户下的所有角色及每个角色下的所有权限 */
	public AdminUser all(int userId) {
		AdminUser adminUser = findById(userId);
		List<AdminRole> adminRoleList = adminUser.getAdminRoleList();
		List<AdminRole> adminRoleList2 = new ArrayList<AdminRole>();
		for (AdminRole adminRole : adminRoleList) {

			int roleId = adminRole.getRoleId();
			AdminRole adminrole = this.adminRoleServiceImpl.findByRoleId(roleId);
			adminRoleList2.add(adminrole);
		}
		adminUser.setAdminRoleList(adminRoleList2);
		return adminUser;
	}
	
	
}
