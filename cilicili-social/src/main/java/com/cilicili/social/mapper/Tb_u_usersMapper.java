package com.cilicili.social.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Tb_u_usersMapper {
	@Select(value="select user_id from tb_u_users where user_name=#{user_name,jdbcType=VARCHAR}")
	String selectByUserName(String user_name);
	
	@Select(value="select user_name from tb_u_users where user_id=#{user_id,jdbcType=VARCHAR}")
	String selectUsernameByUserid(String user_id);
}
