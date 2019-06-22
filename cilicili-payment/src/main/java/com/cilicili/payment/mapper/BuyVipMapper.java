package com.cilicili.payment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.cilicili.payment.domain.BuyVip;

@Mapper
public interface BuyVipMapper {
	@Select(value="select * from viptype left join discount on VipID=vipTypeID where VipID=#{VipID,jdbcType=INTEGER}")
	@Results(value= {
			@Result(column="VipID",property="VipID",jdbcType=JdbcType.INTEGER),
			@Result(column="VipPrice",property="VipPrice",jdbcType=JdbcType.DECIMAL),
			@Result(column="VipName",property="VipName",jdbcType=JdbcType.VARCHAR),
			@Result(column="VipDescribe",property="VipDescribe",jdbcType=JdbcType.VARCHAR),
			@Result(column="VipTime",property="VipTime",jdbcType=JdbcType.DOUBLE),
			@Result(column="discountID",property="discountID",jdbcType=JdbcType.INTEGER),
			@Result(column="discountName",property="discountName",jdbcType=JdbcType.VARCHAR),
			@Result(column="vipTypeID",property="vipTypeID",jdbcType=JdbcType.INTEGER),
			@Result(column="discountValue",property="discountValue",jdbcType=JdbcType.INTEGER),
			
	})
	BuyVip select(Integer VipID);
}
