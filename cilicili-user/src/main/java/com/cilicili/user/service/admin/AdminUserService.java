package com.cilicili.user.service.admin;

import com.cilicili.user.domain.admin.AdminUser;

public interface AdminUserService {

	public AdminUser findByUserName(String userName);
}
