package com.cilicili.payment.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.payment.domain.RedPackets;
import com.cilicili.payment.service.ManageRedPacketsService;

@Controller
@RequestMapping("PaymentMangeReaPackets")
public class PaymentMangeReaPackets {
	@Resource 
	private ManageRedPacketsService manageRedPacketsService;
	@RequestMapping("index")
	public String index(Model model) {
		List<RedPackets> redPacketsList=manageRedPacketsService.select();
		model.addAttribute("redPacketsList", redPacketsList);
		return "payment/manageRedPackets/index";
	}
}
