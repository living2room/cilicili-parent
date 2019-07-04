package com.cilicili.payment.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.cilicili.domain.payment.Orders;

@Mapper
public interface OrderMapper {
	@Insert(value = "insert into tb_order (price,vip_type,payment_id, user_id)"
			+ "values(#{price,jdbcType=DOUBLE},#{vipType,jdbcType=INTEGER},#{paymentID,jdbcType=BIGINT},#{userID,jdbcType=VARCHAR})")
	int insert(Orders orders);
}
