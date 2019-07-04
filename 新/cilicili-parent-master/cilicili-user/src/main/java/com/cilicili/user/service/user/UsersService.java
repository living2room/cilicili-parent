package com.cilicili.user.service.user;

import java.util.List;

import com.cilicili.domain.user.user.Users;

public interface UsersService {

	public Users findByUserName(String userName);

	public Users findByEmail(String email);

	public int addUser(Users user);
	
	/*
	 *通过userId 查找用户的信息及所有角色，多表连接
	*/
	public Users findById(String userId);
	
	/*
	 * 修改密码
	 */
	public int upPassword(String userName,String userPassword);
	
	/*
	 * 查找所有用户（给管理员）
	 */
	public List<Users> findAll();
	
	/*
	 * 禁用用户（给管理员）
	 */
	public int disable(String userName,int status);
}
