package com.cilicili.payment.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GetRedPacketsMapper {
	@Delete(value="delete from tb_get_red_packets where red_packets_id= #{RedPacketsID,jdbcType=INTEGER} and user_id=#{UserID,jdbcType=INTEGER}")
	int delete(int RedPacketsID,int UserID );
}
