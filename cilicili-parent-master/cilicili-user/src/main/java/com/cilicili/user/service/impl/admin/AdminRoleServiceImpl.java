package com.cilicili.user.service.impl.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cilicili.user.domain.admin.AdminRole;
import com.cilicili.user.mapper.admin.AdminRoleMapper;
import com.cilicili.user.service.admin.AdminRoleService;
@Service
public class AdminRoleServiceImpl implements AdminRoleService {

	@Resource
	private AdminRoleMapper adminRoleMapper;
	
	/*
	 * 通过roelId查角色的权限等信息
	 */
	@Override
	public AdminRole findByRoleId(int roleId) {
		
		return this.adminRoleMapper.findByRoleId(roleId);
	}

}
