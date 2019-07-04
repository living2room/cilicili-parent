package com.cilicili.user.service.user;

import com.cilicili.domain.user.user.Role;

public interface RoleService {

	/*
	 * 通过roleId查角色的所有权限
	 * @see com.cilicili.user.service.user.RoleService#findByRoleId(java.lang.String)
	 */
	public Role findByRoleId(String roleId);
}
