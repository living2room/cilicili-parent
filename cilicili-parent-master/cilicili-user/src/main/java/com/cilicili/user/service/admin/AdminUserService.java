package com.cilicili.user.service.admin;

import java.util.List;

import com.cilicili.user.domain.admin.AdminRole;
import com.cilicili.user.domain.admin.AdminUser;

public interface AdminUserService {

	public AdminUser findByUserName(String userName);
	/*
	 *通过userId 查找管理员的信息及所有角色，多表连接
	*/
	public AdminUser findById(int userId);
	
}
