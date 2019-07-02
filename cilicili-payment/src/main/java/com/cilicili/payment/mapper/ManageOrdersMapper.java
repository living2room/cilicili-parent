package com.cilicili.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.cilicili.domain.payment.ManageOrders;

@Mapper
public interface ManageOrdersMapper {
	@Select(value="select * from tb_vip_type,tb_order,tb_already_payment where payment_id=order_id and vip_type=vip_id")
	@Results(value= {
			@Result(column="price",property="price",jdbcType=JdbcType.DOUBLE),
			@Result(column="payment_id",property="payment_id",jdbcType=JdbcType.BIGINT),
			@Result(column="user_id",property="user_id",jdbcType=JdbcType.VARCHAR),
			@Result(column="alipay_id",property="alipay_id",jdbcType=JdbcType.VARCHAR),
			@Result(column="vip_name",property="vip_name",jdbcType=JdbcType.VARCHAR),


	})
	
	List<ManageOrders> findAll();
}
