package com.cilicili.payment.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cilicili.domain.payment.OpenVip;
import com.cilicili.domain.payment.VipEndTime;
import com.cilicili.payment.config.AlipayConfig;
import com.cilicili.payment.config.AlipayReturnConfig;
import com.cilicili.payment.service.AlreadyPaymentService;
import com.cilicili.payment.service.OpenVipService;
import com.cilicili.payment.service.VipEndTimeService;


@Controller
@RequestMapping("PaymentToAlipay")
public class PaymentToAlipay {
	//付款接口
	@RequestMapping("pay")
	//String out_trade_no 是商户订单号，商户网站订单系统中唯一订单号，必填
	//String total_amount 是付款金额，必填
	//String subject 是订单名称，必填
	//String body 是商品描述，可空
	public String pay(String out_trade_no,String total_amount,String subject,String body,Model model) {
		//获得初始化的AlipayClient
		AlipayClient alipayClient=
				new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, 
										AlipayConfig.merchant_private_key, "json", 
										AlipayConfig.charset, AlipayConfig.alipay_public_key, 
										AlipayConfig.sign_type);
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");		
		//请求
		try {
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			model.addAttribute("alipayReturn",result);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//以下为调试代码
//		System.out.println(out_trade_no);
//		System.out.println(total_amount);
//		System.out.println(subject);
//		System.out.println(body);
		//调试代码结束
		return "payment/alipay/payPage.html";
	}
	
	@Resource 
	private VipEndTimeService vipEndTimeService;
	@Resource 
	private OpenVipService openVipService;
	@Resource
	private AlreadyPaymentService alreadyPaymentService;
	
	//验证收款
	@RequestMapping("AlipayReturn")
	public String AlipayReturn(AlipayReturnConfig alipayReturnConfig, Model model) {
		Map<String,String> params=new HashMap<String,String>();
		
		//构造参数params
		params.put("charset", alipayReturnConfig.getCharset());
		params.put("out_trade_no",alipayReturnConfig.getOut_trade_no());
		params.put("method",alipayReturnConfig.getMethod());
		params.put("total_amount",alipayReturnConfig.getTotal_amount());
		params.put("sign",alipayReturnConfig.getSign());
		params.put("trade_no",alipayReturnConfig.getTrade_no());
		params.put("auth_app_id",alipayReturnConfig.getAuth_app_id());
		params.put("version",alipayReturnConfig.getVersion());
		params.put("app_id",alipayReturnConfig.getApp_id());
		params.put("sign_type",alipayReturnConfig.getSign_type());
		params.put("seller_id",alipayReturnConfig.getSeller_id());
		params.put("timestamp",alipayReturnConfig.getTimestamp());
		
		//验证签名
		try {
			boolean signVerified=AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
										AlipayConfig.charset, AlipayConfig.sign_type);
			
			model.addAttribute("status","failure");
			if(signVerified) {
				OpenVip openVip=openVipService.select(Long.parseLong(alipayReturnConfig.getOut_trade_no()));
				Long vipEndTime=openVip.getVipTime()+System.currentTimeMillis()/1000;
				
				System.out.println(openVip.getUserID()+"???");
			VipEndTime Db_vipEndTime = this.vipEndTimeService.select(openVip.getUserID());
			System.out.println(openVip.getUserID()+"???");
			
				if(Db_vipEndTime==null) {
					//说明数据库中没有该用户信息，即该用户不是vip用户
					vipEndTimeService.insert(openVip.getUserID(),vipEndTime);
				}else{
					//数据库中存在该用户数据
					if(Db_vipEndTime.getVipEndTime()>=(System.currentTimeMillis() / 1000)) {
						//说明该用户vip没有过期
						//增加时间
						vipEndTimeService.update(openVip.getVipTime()+Db_vipEndTime.getVipEndTime(), openVip.getUserID());
					}else {
						//该用户vip已经过期（现在不是vip）
						vipEndTimeService.update(vipEndTime,openVip.getUserID());
					}
					
				}
				
				
				alreadyPaymentService.insert(Long.parseLong(alipayReturnConfig.getOut_trade_no()), alipayReturnConfig.getTrade_no());
				
				//订单号
				model.addAttribute("out_trade_no",alipayReturnConfig.getOut_trade_no());
				//支付宝交易号
				model.addAttribute("trade_no",alipayReturnConfig.getTrade_no());
				//付款金额
				model.addAttribute("total_amount",alipayReturnConfig.getTotal_amount());
				model.addAttribute("status","success");
				
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "payment/alipay/alipayReturn.html";
	}
	
	
}
