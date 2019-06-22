package com.cilicili.user.service.admin;

import com.cilicili.user.domain.admin.AdminRole;

public interface AdminRoleService {

	/*
	 * 通过adminRoleId查角色的所有权限
	 * @see com.cilicili.user.service.user.RoleService#findByRoleId(java.lang.String)
	 */
	public AdminRole findByRoleId(int roleId);
}
