package com.cilicili.user.service.impl.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cilicili.user.domain.user.UsersMessage;
import com.cilicili.user.mapper.user.UsersMessageMapper;
import com.cilicili.user.service.user.UsersMessageService;

@Service
public class UsersMessageServiceImpl implements UsersMessageService {

	@Resource
	private UsersMessageMapper usersMessageMapper;

	public UsersMessage findByUserName(String userName) {
		
		return this.usersMessageMapper.findByUserName(userName);
	}
	
	public int upUsersMessage(UsersMessage usersMessage) {
	   return this.usersMessageMapper.upUsersMessage(usersMessage);
	}
}
