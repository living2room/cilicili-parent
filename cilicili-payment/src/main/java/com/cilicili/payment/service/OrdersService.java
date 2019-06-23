package com.cilicili.payment.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cilicili.domain.payment.Orders;
import com.cilicili.payment.mapper.OrderMapper;

@Service
public class OrdersService {
	@Resource
	private OrderMapper ordersMapper;
	public int insert(Orders orders) {
		return ordersMapper.insert(orders);
	}
}
