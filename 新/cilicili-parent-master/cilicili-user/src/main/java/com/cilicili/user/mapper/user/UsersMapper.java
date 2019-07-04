package com.cilicili.user.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cilicili.domain.user.user.Users;



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
	
	/*
	 *通过userId 查找管理员的所有角色的信息，多表连接
	 */
	public Users findById(String userId);
	
	/*
	 * 修改密码
	 */
	public int upPassword(@Param("userName")String userName,@Param("userPassword") String userPassword);
	
	/*
	 * 查找所有用户（给管理员）
	 */
	public List<Users> findAll();
	
	/*
	 * 禁用用户（给管理员）
	 */
	public int disable(@Param("userName")String userName,@Param("status")int status);
}