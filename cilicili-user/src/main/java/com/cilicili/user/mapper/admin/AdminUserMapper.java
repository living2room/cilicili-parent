package com.cilicili.user.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cilicili.user.domain.admin.AdminUser;

@Mapper
public interface AdminUserMapper {

	/*
	 * 根据用户名查该用户所有信息
	 */
	public AdminUser findByUserName(@Param("userName") String userName);
	
	/*
	 * 
	 */
}