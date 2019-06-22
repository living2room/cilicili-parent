package com.cilicili.payment.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.cilicili.payment.domain.VipEndTime;

@Mapper
public interface VipEndTimeMapper {
	@Insert(value="insert into tb_vip_end_time(user_id,vip_end_time) values(#{userID,jdbcType=VARCHAR},#{vipEndTime,jdbcType=BIGINT})")
	int insert(String userID,Long vipEndTime);
	
	@Select(value="select * from tb_vip_end_time where user_id=#{user_id,jdbcType=VARCHAR}")
	@Results(value= {
			@Result(column="id",property="ID",jdbcType=JdbcType.INTEGER),
			@Result(column="user_id",property="userID",jdbcType=JdbcType.INTEGER),
			@Result(column="vip_end_time",property="vipEndTime",jdbcType=JdbcType.BIGINT),			
	})
	VipEndTime select(String userID);
	
	@Update(value="update tb_vip_end_time set vip_end_time= #{vipEndTime,jdbcType=BIGINT} where user_id=#{userID,jdbcType=VARCHAR}")	
	int update(Long vipEndTime,String userID);
	
}
