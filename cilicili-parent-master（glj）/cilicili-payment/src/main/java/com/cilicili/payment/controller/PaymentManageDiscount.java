package com.cilicili.payment.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.payment.domain.Discount;
import com.cilicili.payment.domain.VipType;
import com.cilicili.payment.service.ManageDiscountService;
import com.cilicili.payment.service.VipTypeService;

@Controller
@RequestMapping("PaymentManageDiscount")
public class PaymentManageDiscount {
	@Resource
	private ManageDiscountService manageDiscountService;
	@RequestMapping("index")
	public String Index(Model model) {
		List<Discount> discountList = manageDiscountService.getDate();
		model.addAttribute("discountList", discountList);
		return "payment/manageDiscount/index.html";
	}
	
	@RequestMapping("update")
	public String update(String discountName,String discountValue ,String VipID,Model model) {
		Discount discount=new Discount();
		discount.setVipID(Integer.parseInt(VipID));
		discount.setDiscountName(discountName);
		discount.setDiscountValue(Integer.parseInt(discountValue));
//		System.out.println(discount);
		int updateStatus=manageDiscountService.update(discount);
		if(discountValue=="10") {
			manageDiscountService.deleteByVipTypeID(discount.getVipID());
		}
		if(updateStatus==1) {
			model.addAttribute("javascript","<script>alert('修改成功!');location='index'</script>");
		}else if(updateStatus==0){
		   manageDiscountService.insert(discount);
		   model.addAttribute("javascript","<script>alert('修改失败!');location='index'</script>"); 
		}
		return "payment/manageDiscount/redirect.html";
	}
}
