package com.cilicili.social.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.cilicilil.domain.social.Tb_subscribe;

@Mapper
public interface Tb_subscribeMapper {
	@Insert(value="insert into tb_subscribe(user_id,to_user_id)values(#{user_id,jdbcType=VARCHAR},#{to_user_id,jdbcType=VARCHAR})")
	int insert(String user_id,String to_user_id);
	
	@Select(value="select * from tb_subscribe where user_id=#{user_id,jdbcType=VARCHAR} and to_user_id=#{to_user_id}")
	@Results(value= {
			@Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
			@Result(column="user_id", property="user_id", jdbcType=JdbcType.VARCHAR),
			@Result(column="to_user_id", property="to_user_id", jdbcType=JdbcType.VARCHAR),
	})
	Tb_subscribe select(String user_id,String to_user_id);
	
	@Delete(value="delete from tb_subscribe where user_id=#{user_id,jdbcType=VARCHAR} and to_user_id=#{to_user_id,jdbcType=VARCHAR}")
	int delete(String user_id,String to_user_id);
}
