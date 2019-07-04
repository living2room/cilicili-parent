package com.cilicili.payment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.cilicili.domain.payment.OpenVip;

@Mapper
public interface OpenVipMapper {
	@Select(value="select vip_time,user_id from tb_order inner join tb_vip_type on vip_id=vip_type where payment_id=#{paymentID,jdbcType=BIGINT} ")
	@Results(value= {
			@Result(column="vip_time",property="vipTime",jdbcType=JdbcType.DOUBLE),
			@Result(column="user_id",property="userID",jdbcType=JdbcType.INTEGER),
	})
	OpenVip select(Long paymentID);
}
