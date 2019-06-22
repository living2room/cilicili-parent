package com.cilicili.user.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import com.cilicili.domain.user.user.Role;

@Mapper
public interface RoleMapper {

	/*
	 * 通过roelId查角色的权限等信息
	 */
	public Role findByRoleId(String roleId);
}