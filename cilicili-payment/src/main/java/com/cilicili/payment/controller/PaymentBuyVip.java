package com.cilicili.payment.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.payment.domain.BuyVip;
import com.cilicili.payment.domain.RedPackets;
import com.cilicili.payment.domain.VipType;
import com.cilicili.payment.service.BuyVipService;
import com.cilicili.payment.service.ManageDiscountService;
import com.cilicili.payment.service.ManageRedPacketsService;
import com.cilicili.payment.service.VipTypeService;

@Controller
@RequestMapping("PaymentBuyVip")
public class PaymentBuyVip {
	@Resource 
	private VipTypeService vipTypeService;
	
	@RequestMapping("Buy")
	public String Buy(Model model) {
		List<VipType> vipTypeList= vipTypeService.findAll();
		model.addAttribute("vipTypeList", vipTypeList);
		return "payment/buyVip/buyVip";
	}
	
	@Resource
	private ManageRedPacketsService manageRedPacketsService;
	@Resource 
	private ManageDiscountService manageDiscountService;
	@Resource 
	private BuyVipService buyVipService;
	@RequestMapping("Buy.do")
	public String BuyDo(String VipID,String userID ,Model model) {
		if(Integer.parseInt(userID)==0 || Integer.parseInt(VipID)==0) {
			//如果用户ID或者VipID不合法那么执行以下代码
			
		}
		
		List<RedPackets> redPacketsList= manageRedPacketsService.selectByUserID(Integer.parseInt(userID));
		model.addAttribute("redPacketsList", redPacketsList);

		BuyVip buyVip=buyVipService.select(Integer.parseInt(VipID));
		model.addAttribute("buyVip", buyVip);
		return "payment/buyVip/buyVipDo";
	}

}
