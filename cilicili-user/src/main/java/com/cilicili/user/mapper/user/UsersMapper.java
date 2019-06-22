package com.cilicili.user.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cilicili.user.domain.user.Users;



@Mapper
public interface UsersMapper {

	/*
	 * 根据用户名查该用户所有信息
	 */
	public Users findByUserName(@Param("userName") String userName);
	
	/*
	 *根据Email查数据库是否已存在
	 */
	public Users findByEmail(String email);
	
	/*
	 * 用户注册  
	 */
	public int addUser(Users user);
	
}