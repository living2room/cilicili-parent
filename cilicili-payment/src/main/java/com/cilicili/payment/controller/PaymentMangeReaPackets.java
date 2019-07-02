package com.cilicili.payment.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cilicili.domain.payment.RedPackets;
import com.cilicili.payment.service.ManageRedPacketsService;
import com.cilicili.payment.service.RedPacketsService;

@Controller
@RequestMapping("PaymentMangeReaPackets")
public class PaymentMangeReaPackets {
	@Resource 
	private ManageRedPacketsService manageRedPacketsService;
	@RequestMapping("index")
	public String index(Model model ,String alert) {
		List<RedPackets> redPacketsList=manageRedPacketsService.select();
		model.addAttribute("redPacketsList", redPacketsList);
		model.addAttribute("alert",alert);
		System.out.println(redPacketsList);
		return "payment/manageRedPackets/index";
	}
	@Resource
	private RedPacketsService redPacketsService;
	@RequestMapping("delete")
	public String delete(String redPacketsID,Model model) {
		Integer id=Integer.parseInt(redPacketsID);
		redPacketsService.delete(id);
		model.addAttribute("javascript","<script>alert('删除成功!');location='index';</script>");
		return "payment/manageRedPackets/redirect";
	}
	
	@RequestMapping("add")
	public String add(Model model,String redPacketsName,String redPacketsDescribe,String redPacketsValue) {
		redPacketsService.insert(redPacketsName, redPacketsDescribe, Double.parseDouble(redPacketsValue));
		model.addAttribute("javascript","<script>alert('添加成功!');location='index';</script>");
		return "payment/manageRedPackets/redirect";
	}
	
	@RequestMapping("getRedPackets")
	@ResponseBody
	public String getRedPackets(String userID,String red_packets_id) {
		if(redPacketsService.get_red_packets(Integer.parseInt(red_packets_id),userID)==1) {
			return "success";
		}else {
			return "failure";
		}
		
	}
}
