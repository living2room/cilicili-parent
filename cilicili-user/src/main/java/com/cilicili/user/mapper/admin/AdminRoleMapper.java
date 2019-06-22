package com.cilicili.user.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cilicili.user.domain.admin.AdminRole;

@Mapper
public interface AdminRoleMapper {

	/*
	 * 通过roelId查角色的权限等信息
	 */
	public AdminRole findByRoleId(int roleId);
}