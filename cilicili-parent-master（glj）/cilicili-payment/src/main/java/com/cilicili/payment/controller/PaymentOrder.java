package com.cilicili.payment.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cilicili.payment.config.AlipayConfig;
import com.cilicili.payment.domain.BuyVip;
import com.cilicili.payment.domain.Orders;
import com.cilicili.payment.domain.RedPackets;
import com.cilicili.payment.service.BuyVipService;
import com.cilicili.payment.service.GetRedPacketsService;
import com.cilicili.payment.service.ManageRedPacketsService;
import com.cilicili.payment.service.OrdersService;
import com.cilicili.payment.service.RedPacketsService;

@Controller
@RequestMapping("PaymentOrder")
public class PaymentOrder {
	@Resource 
	private BuyVipService buyVipService;
	@Resource
	private ManageRedPacketsService manageRedPacketsService;
	@Resource 
	private OrdersService orderService;
	@Resource 
	private GetRedPacketsService getRedPacketsService; 
	@Resource 
	private RedPacketsService redPackestService;

	
	@RequestMapping("create")
	public String create(String userID, String redPacketsID,String vipID,Model model) {

		BuyVip buyVip=buyVipService.select(Integer.parseInt(vipID));
		List<RedPackets> redPacketsList= manageRedPacketsService.selectByUserID(Integer.parseInt(userID));
		double Db_price=buyVip.getVipPrice();
		Integer Db_vipID=Integer.parseInt(vipID);
		Integer Db_userID=Integer.parseInt(userID);
		long Db_paymentID=System.currentTimeMillis()/1000;
		
		Db_price=buyVip.getDiscountValue()*0.1*Db_price;
		
		for (RedPackets redPackets : redPacketsList) {
			if(redPackets.getRedPacketsID()==Integer.parseInt(redPacketsID)) {
				redPackestService.delete(Integer.parseInt(redPacketsID));
				Db_price=Db_price-redPackets.getRedPacketsPrice();
			}
		}
		
		Orders orders=new Orders();
		orders.setPaymentID(Db_paymentID);
		orders.setPrice(Db_price);
		orders.setUserID(Db_userID);
		orders.setVipType(Db_vipID);
		orderService.insert(orders);
//		//接入支付宝
//		
		//String out_trade_no 是商户订单号，商户网站订单系统中唯一订单号，必填
		//String total_amount 是付款金额，必填
		//String subject 是订单名称，必填
		//String body 是商品描述，可空
		AlipayClient alipayClient=
				new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, 
										AlipayConfig.merchant_private_key, "json", 
										AlipayConfig.charset, AlipayConfig.alipay_public_key, 
										AlipayConfig.sign_type);
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ Db_paymentID +"\"," 
				+ "\"total_amount\":\""+ Db_price +"\"," 
				+ "\"subject\":\""+ buyVip.getVipName() +"\"," 
				+ "\"body\":\""+ "" +"\"," //body设置为空
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
//		
		//请求
		try {
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			model.addAttribute("javascript",result);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "payment/order/redirect";
	}

}
