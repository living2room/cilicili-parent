package com.cilicili.user.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cilicili.user.domain.admin.AdminRole;
import com.cilicili.user.domain.admin.AdminUser;

@Mapper
public interface AdminUserMapper {

	/*
	 * 根据用户名查该用户所有信息
	 */
	public AdminUser findByUserName(@Param("userName") String userName);
	
	/*
	 *通过userId 查找管理员的所有角色的信息，多表连接
	 */
	public AdminUser findById(int userId);
}