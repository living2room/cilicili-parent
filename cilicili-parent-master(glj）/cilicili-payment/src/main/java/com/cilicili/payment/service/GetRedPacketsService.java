package com.cilicili.payment.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.payment.mapper.GetRedPacketsMapper;

@Service
public class GetRedPacketsService {
	@Resource 
	private GetRedPacketsMapper getRedPacketsMapper;
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public int delete(int id,int userID) {
		return getRedPacketsMapper.delete(id, userID);
	}
}
