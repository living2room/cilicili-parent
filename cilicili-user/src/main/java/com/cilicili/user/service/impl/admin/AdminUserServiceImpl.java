package com.cilicili.user.service.impl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cilicili.user.domain.admin.AdminUser;
import com.cilicili.user.mapper.admin.AdminUserMapper;
import com.cilicili.user.service.admin.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserMapper adminUserMapper;

	@Override
	public AdminUser findByUserName(String userName) {
		return this.adminUserMapper.findByUserName(userName);
	}
}
