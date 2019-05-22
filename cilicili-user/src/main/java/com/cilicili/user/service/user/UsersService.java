package com.cilicili.user.service.user;

import com.cilicili.user.domain.user.Users;

public interface UsersService {

	public Users findByUserName(String userName);

	public Users findByEmail(String email);

	public int addUser(Users user);
	
}
