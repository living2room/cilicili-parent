package com.cilicili.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.cilicili.payment.domain.RedPackets;

@Mapper
public interface RedPacketsMapper {
	@Select(value="select * from redPackets left join getRedPackets on redPackets.ID=RedPacketsID")
	@Results(value= {
			@Result(column="ID",property="redPacketsID",jdbcType=JdbcType.INTEGER),
			@Result(column="RedPacketsName",property="redPacketsName",jdbcType=JdbcType.VARCHAR),
			@Result(column="RedPacketsDescribe",property="redPacketsDescribe",jdbcType=JdbcType.VARCHAR),
			@Result(column="RedPacketsPrice",property="redPacketsPrice",jdbcType=JdbcType.DOUBLE),
			@Result(column="UserID",property="userID",jdbcType=JdbcType.INTEGER)
			
	})
	List<RedPackets> select();
	
	@Select(value="select * from redPackets left join getRedPackets on redPackets.ID=RedPacketsID where UserID=#{UserID,jdbcType=INTEGER}")
	@Results(value= {
			@Result(column="ID",property="redPacketsID",jdbcType=JdbcType.INTEGER),
			@Result(column="RedPacketsName",property="redPacketsName",jdbcType=JdbcType.VARCHAR),
			@Result(column="RedPacketsDescribe",property="redPacketsDescribe",jdbcType=JdbcType.VARCHAR),
			@Result(column="RedPacketsPrice",property="redPacketsPrice",jdbcType=JdbcType.DOUBLE),
			@Result(column="UserID",property="userID",jdbcType=JdbcType.INTEGER)
			
	})
	List<RedPackets> selectByUserID(Integer UserID);
	
}
