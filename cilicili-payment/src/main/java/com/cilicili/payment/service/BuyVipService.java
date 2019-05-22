package com.cilicili.payment.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.payment.domain.BuyVip;
import com.cilicili.payment.mapper.BuyVipMapper;

@Service
public class BuyVipService {
	@Resource
	private BuyVipMapper buyVipMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public BuyVip select(Integer vipID) {
		return buyVipMapper.select(vipID);
	} 
	
	
}
