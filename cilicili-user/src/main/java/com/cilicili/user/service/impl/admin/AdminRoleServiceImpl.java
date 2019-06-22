package com.cilicili.user.service.impl.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cilicili.domain.user.admin.AdminRole;
import com.cilicili.user.mapper.admin.AdminRoleMapper;
import com.cilicili.user.service.admin.AdminRoleService;

@Service
public class AdminRoleServiceImpl implements AdminRoleService{

	@Resource
	private AdminRoleMapper adminRoleMapper;
	/*
	 * 通过roleId查角色的所有权限
	 * @see com.cilicili.user.service.user.RoleService#findByRoleId(java.lang.String)
	 */
	@Override
	public AdminRole findByRoleId(int roleId) {
		return this.adminRoleMapper.findByRoleId(roleId);
	};
}
