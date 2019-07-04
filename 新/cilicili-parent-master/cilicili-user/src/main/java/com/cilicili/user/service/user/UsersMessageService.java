package com.cilicili.user.service.user;

import com.cilicili.domain.user.user.UsersMessage;

public interface UsersMessageService {

	// 注册的时候要往messag里加入记录
	public int addMessage(String userId, String userName);

	public UsersMessage findByUserName(String userName);

	public int upUsersMessage(UsersMessage usersMessage);
}
