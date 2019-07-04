package com.cilicili.payment.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.domain.payment.OpenVip;
import com.cilicili.payment.mapper.OpenVipMapper;

@Service
public class OpenVipService {
	@Resource 
	private OpenVipMapper openVipMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public OpenVip select(Long paymentID) {
		return openVipMapper.select(paymentID);
	}
}
