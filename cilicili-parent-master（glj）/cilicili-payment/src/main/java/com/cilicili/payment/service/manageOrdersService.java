package com.cilicili.payment.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.payment.domain.ManageOrders;
import com.cilicili.payment.mapper.ManageOrdersMapper;

@Service
public class manageOrdersService {
	@Resource
	private ManageOrdersMapper manageOrdersMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<ManageOrders> findAll(){
		return manageOrdersMapper.findAll();
	}
	
}
