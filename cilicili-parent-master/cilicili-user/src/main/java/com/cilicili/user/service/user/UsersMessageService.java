package com.cilicili.user.service.user;

import com.cilicili.user.domain.user.UsersMessage;

public interface UsersMessageService {

	public UsersMessage findByUserName(String userName);

	public int upUsersMessage(UsersMessage usersMessage);
}
