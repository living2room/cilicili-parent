package com.cilicili.user.service.impl.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cilicili.domain.user.user.Role;
import com.cilicili.user.mapper.user.RoleMapper;
import com.cilicili.user.service.user.RoleService;
@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper roleMapper;
/*
 * 通过roleId查角色的所有权限
 * @see com.cilicili.user.service.user.RoleService#findByRoleId(java.lang.String)
 */
	public Role findByRoleId(String roleId) {

		return this.roleMapper.findByRoleId(roleId);
	};
}
