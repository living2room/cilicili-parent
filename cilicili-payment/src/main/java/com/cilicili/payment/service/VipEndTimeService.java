package com.cilicili.payment.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.payment.domain.VipEndTime;
import com.cilicili.payment.mapper.VipEndTimeMapper;

@Service
public class VipEndTimeService {
	@Resource
	private VipEndTimeMapper vipEndTimeMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public int insert(String userID,Long vipEndTime) {
		return vipEndTimeMapper.insert(userID, vipEndTime);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public int update(Long vipEndTime,String userID) {
		return vipEndTimeMapper.update(vipEndTime, userID);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public VipEndTime select(String userID) {
		return vipEndTimeMapper.select(userID);
	}
}
