package com.cilicili.payment.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.payment.domain.Discount;
import com.cilicili.payment.mapper.ManageDiscountMapper;

@Service
public class ManageDiscountService {
	@Resource ManageDiscountMapper manageDiscountMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Discount> getDate() {
		List<Discount> discountList=manageDiscountMapper.select();
		return discountList;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public int update(Discount discount) {
		return manageDiscountMapper.update(discount);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public int insert(Discount discount) {
		return manageDiscountMapper.insert(discount);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor= {Exception.class})
	public int deleteByVipTypeID(int VipTypeID) {
		return manageDiscountMapper.deleteByVipTypeID(VipTypeID);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Discount selectByID(Integer vipTypeID){
		return manageDiscountMapper.selectByID(vipTypeID);
	}
}
