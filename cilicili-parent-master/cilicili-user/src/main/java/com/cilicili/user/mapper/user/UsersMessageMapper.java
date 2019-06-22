package com.cilicili.user.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cilicili.user.domain.user.UsersMessage;

@Mapper
public interface UsersMessageMapper {

	// 注册的时候要往messag里加入记录
	public int addMessage(@Param("userId") String userId, @Param("userName") String userName);

	public UsersMessage findByUserName(@Param("userName") String userName);

	public int upUsersMessage(UsersMessage usersMessage);

}
