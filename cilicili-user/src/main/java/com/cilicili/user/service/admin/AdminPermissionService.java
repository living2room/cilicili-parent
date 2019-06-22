package com.cilicili.user.service.admin;

import java.util.List;

import com.cilicili.domain.user.admin.AdminPermission;


public interface AdminPermissionService {
	
	//根据父节点查二级菜单
	public List<AdminPermission> Two(int parentId); 
}
