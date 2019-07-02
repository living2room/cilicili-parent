package com.cilicili.payment.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.payment.mapper.RedPacketsMapper;

@Service
public class RedPacketsService {
	@Resource
	private RedPacketsMapper redPacketsMapper;
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public int delete(Integer id) {
		return redPacketsMapper.delete(id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public int insert(String redPacketsName,String redPacketsDescribe,double redPacketsValue) {
		return redPacketsMapper.insert(redPacketsName, redPacketsDescribe, redPacketsValue);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public int get_red_packets(int red_packets_id,String user_id) {
		return redPacketsMapper.get_red_packets(red_packets_id, user_id);
	}
}
