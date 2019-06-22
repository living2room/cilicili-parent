package com.cilicili.payment.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.payment.domain.RedPackets;
import com.cilicili.payment.mapper.RedPacketsMapper;

@Service
public class ManageRedPacketsService {
	@Resource
	private RedPacketsMapper redPacketsMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public List<RedPackets> select(){
		return redPacketsMapper.select();
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<RedPackets> selectByUserID(Integer UserID){
		return redPacketsMapper.selectByUserID( UserID);
	}
	
}
