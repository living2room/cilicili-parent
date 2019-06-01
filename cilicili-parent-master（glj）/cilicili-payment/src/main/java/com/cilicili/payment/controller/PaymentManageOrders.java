package com.cilicili.payment.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.payment.domain.ManageOrders;
import com.cilicili.payment.service.manageOrdersService;

@Controller
@RequestMapping("PaymentManageOrders")	
public class PaymentManageOrders {
	@Resource
	private manageOrdersService manageOrderService;
	
	@RequestMapping("findAll")
	public String findAll(Model model) {
		List<ManageOrders> manageOrdersList =manageOrderService.findAll();
		model.addAttribute("manageOrdersList", manageOrdersList);
		return "/payment/manageOrders/findAll";
	} 
}
