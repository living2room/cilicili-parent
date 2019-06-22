package com.cilicili.advertisement.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.cilicili.advertisement.domin.Buttle_Adv;
import com.cilicili.advertisement.service.ButtleService;


@Controller("buttleCon")
@RequestMapping("/buttle")
public class ButtleController {

	@Resource
	private ButtleService buttleService;
	
	//ButtleService buttleService1 = new ButtleService();
	
	@RequestMapping("into")
	public String intobuttle() {
		System.out.println("2222244444");
		Buttle_Adv butt=new Buttle_Adv();
		Date date = new Date();
		butt.setType(1);
		butt.setUrl("http:www.baidu.com");
		butt.setImgSrc("image/pic.png");
		butt.setAlt("测试用例");
		butt.setRemark("Remark");
		butt.setCreateTime(new Timestamp(date.getTime()));
		butt.setOperatorId(3);
		butt.setStatus(1);
		buttleService.addAdvByOne(butt);;
		return "hello";
	}
	
	@RequestMapping("buttleService")
	@ResponseBody
	public String ButtleService(@RequestBody List<Buttle_Adv> itemList) {
		return "";
	}
	
	@RequestMapping("del")
	public String delbuttle() {
		
		buttleService.delAdvById(1);
		return "hello";
	}
	
	@RequestMapping("delmore")
	public String delmorebuttle() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		list.add(4);
		buttleService.delMoreAdv(list);
		return "hello";
	}
	
	@RequestMapping("selectall")
	public String selectall() {
		List<Buttle_Adv> selAdvAll = buttleService.selAdvAll();
		for (Buttle_Adv buttle_Adv : selAdvAll) {
			System.out.println(buttle_Adv);
		}
		return "hello";
	}
	
	@RequestMapping("selectone")
	public String selectone() {
		Buttle_Adv selAdvone = buttleService.selAdvById(5);
		System.out.println(selAdvone);
		return "hello";
	}
	
	@RequestMapping("modify")
	public String modifyone() {
		Buttle_Adv selAdvone = buttleService.selAdvById(5);
		System.out.println(selAdvone);
		
		selAdvone.setRemark("updateRemark");
		buttleService.upAdvByOBJ(selAdvone);
		System.out.println(buttleService.selAdvById(5));
		return "hello";
	}
}
