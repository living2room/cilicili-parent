package com.cilicili.payment.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlreadyPaymentMapper {
	@Insert(value="insert into tb_already_payment(order_id,alipay_id)values(#{orderID,jdbcType=BIGINT},#{alipayID,jdbcType=LONGVARCHAR})")
	int insert(Long orderID,String alipayID);
}
