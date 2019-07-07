package com.cilicili.payment.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.domain.payment.VipEndTime;
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
	
	//查用户是否为VIP
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public int vip(HttpServletRequest request) {
		HttpSession httpSession =request.getSession();
		String userID = httpSession.getAttribute("userID").toString();
		VipEndTime vipEndTime = vipEndTimeMapper.select(userID);
		if(vipEndTime !=null) {
			return 1;
		}else {
			return 0;
		}
	}
}
