package com.cilicili.payment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.cilicili.payment.domain.BuyVip;

@Mapper
public interface BuyVipMapper {
	@Select(value="select * from tb_vip_type left join tb_discount on vip_id=vip_type_id where vip_id=#{VipID,jdbcType=INTEGER}")
	@Results(value= {
			@Result(column="vip_id",property="VipID",jdbcType=JdbcType.INTEGER),
			@Result(column="vip_price",property="VipPrice",jdbcType=JdbcType.DECIMAL),
			@Result(column="vip_name",property="VipName",jdbcType=JdbcType.VARCHAR),
			@Result(column="vip_describe",property="VipDescribe",jdbcType=JdbcType.VARCHAR),
			@Result(column="vip_time",property="VipTime",jdbcType=JdbcType.DOUBLE),
			@Result(column="discount_id",property="discountID",jdbcType=JdbcType.INTEGER),
			@Result(column="discount_name",property="discountName",jdbcType=JdbcType.VARCHAR),
			@Result(column="vip_type_id",property="vipTypeID",jdbcType=JdbcType.INTEGER),
			@Result(column="discount_value",property="discountValue",jdbcType=JdbcType.INTEGER),
			
	})
	BuyVip select(Integer VipID);
}
