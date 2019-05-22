package com.cilicili.user.service.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cilicili.user.domain.user.Users;
import com.cilicili.user.mapper.user.UsersMapper;
import com.cilicili.user.service.user.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersMapper usersMapper;

	@Override
	public Users findByUserName(String userName) {
		return this.usersMapper.findByUserName(userName);
	}

	public int addUser(Users user) {
		return this.usersMapper.addUser(user);
	}
	
	public Users findByEmail(String email) {
		return this.usersMapper.findByEmail(email);
	}
}
