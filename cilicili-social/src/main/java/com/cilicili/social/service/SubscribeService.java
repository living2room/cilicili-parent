package com.cilicili.social.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.social.domain.Tb_subscribe;
import com.cilicili.social.mapper.Tb_subscribeMapper;

@Service
public class SubscribeService {
	
	@Resource 
	private Tb_subscribeMapper tb_subscribeMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public int insert(String user_id,String to_user_id) {
		return tb_subscribeMapper.insert(user_id, to_user_id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Tb_subscribe select (String user_id,String to_user_id) {
		return tb_subscribeMapper.select(user_id, to_user_id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String user_id,String to_user_id) {
		return tb_subscribeMapper.delete(user_id, to_user_id);
	}
}
