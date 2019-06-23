package com.cilicili.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cilicili.domain.payment.ManageOrders;

@Mapper
public interface ManageOrdersMapper {
	@Select(value="select * from tb_vip_type,tb_order,tb_already_payment where payment_id=order_id and vip_type=vip_id")
	List<ManageOrders> findAll();
}
