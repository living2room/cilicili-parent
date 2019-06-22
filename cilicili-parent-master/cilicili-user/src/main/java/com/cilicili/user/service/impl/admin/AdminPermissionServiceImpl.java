package com.cilicili.user.service.impl.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cilicili.user.domain.admin.AdminPermission;
import com.cilicili.user.mapper.admin.AdminPermissionMapper;
import com.cilicili.user.service.admin.AdminPermissionService;

@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {

	@Resource
	private AdminPermissionMapper adminPermissionMapper;

	// 根据父节点查二级菜单
	@Override
	public List<AdminPermission> Two(int parentId) {
	   return this.adminPermissionMapper.Two(parentId);
	}

}
