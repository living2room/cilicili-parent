package com.cilicili.payment.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.payment.domain.VipType;
import com.cilicili.payment.mapper.VipTypeMapper;

@Service
public class VipTypeService {
	@Resource 
	private VipTypeMapper vipTypeMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<VipType> findAll(){
		return vipTypeMapper.findAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public int add(VipType vipType) {
		return vipTypeMapper.insert(vipType);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public int delete(String VipID) {
		return vipTypeMapper.delete(VipID);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public VipType selectById(String VipID) {
		return vipTypeMapper.selectById(VipID);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public int updateDo(VipType vipType) {
		return vipTypeMapper.updateDo(vipType);
	}
}
