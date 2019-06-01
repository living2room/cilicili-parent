package com.cilicili.user.service.admin;

import java.util.List;

import com.cilicili.user.domain.admin.AdminRole;

public interface AdminRoleService {

		/*
		 * 通过roelId查角色的权限等信息
		 */
		public AdminRole findByRoleId(int roleId);

}
