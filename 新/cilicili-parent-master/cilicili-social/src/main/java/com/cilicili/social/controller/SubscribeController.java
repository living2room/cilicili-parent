package com.cilicili.social.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cilicili.social.service.SubscribeService;

@Controller
@RequestMapping("Subscribe")
public class SubscribeController {
	@Resource
	private SubscribeService subscribeService;
	
	@RequestMapping("Add")
	@ResponseBody
	public String Add(String user_id,String to_user_id) {
		subscribeService.delete(user_id, to_user_id);
		subscribeService.insert(user_id, to_user_id);
		return "OK";
	}
	
	
	@RequestMapping("Remove")
	@ResponseBody
	public String Remove(String user_id,String to_user_id) {
		subscribeService.delete(user_id, to_user_id);
		return "OK";
	}
	
	@RequestMapping("Find")
	@ResponseBody
	public String Find(String user_id,String to_user_id) {
		if (subscribeService.select(user_id, to_user_id)!=null) {
			return "subscribed";
		}else {
			return "no_subscribed";
		}
	}
}
