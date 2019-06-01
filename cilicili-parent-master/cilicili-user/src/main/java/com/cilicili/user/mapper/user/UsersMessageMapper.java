package com.cilicili.user.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cilicili.user.domain.user.UsersMessage;

@Mapper
public interface UsersMessageMapper {
   
	public UsersMessage findByUserName(@Param("userName") String userName);
	public int upUsersMessage(UsersMessage usersMessage);
	
}
