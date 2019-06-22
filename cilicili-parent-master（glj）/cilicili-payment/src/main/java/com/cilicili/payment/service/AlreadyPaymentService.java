package com.cilicili.payment.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.payment.mapper.AlreadyPaymentMapper;

@Service
public class AlreadyPaymentService {
	@Resource 
	private AlreadyPaymentMapper alreadyPaymentMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public int insert(Long orderID,String alipayID) {
		return alreadyPaymentMapper.insert(orderID, alipayID);
	}
}
