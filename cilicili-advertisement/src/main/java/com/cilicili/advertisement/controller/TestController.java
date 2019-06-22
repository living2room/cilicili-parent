package com.cilicili.advertisement.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.advertisement.domain.AdvertiseType;
import com.cilicili.advertisement.service.AdvertiseTypeService;


@Controller
@RequestMapping("/test")
public class TestController {

//	private ButtleController b = new ButtleController();
//	@Test
//	public void insert() {
//		Buttle_Adv butt=new Buttle_Adv();
//		Date date = new Date();
//		butt.setType(1);
//		butt.setUrl("http:www.baidu.com");
//		butt.setImg_Src("image/pic.png");
//		butt.setAlt("测试用例");
//		butt.setRemark("Remark");
//		butt.setCreate_Time(new Timestamp(date.getTime()));
//		butt.setOperator_Id(3);
//		butt.setStatus(1);
//		b.ButtleService1(butt);
//	}

	@Resource
	private AdvertiseTypeService advertiseTypeService;
	
	/*
	 * @RequestMapping("wel") public String wel(HttpServletRequest request) {
	 * //System.out.println("Ttttttttt"); List<AdvertiseType> list
	 * =advertiseTypeService.selAdvAll(request); HttpSession session =
	 * request.getSession(); session.setMaxInactiveInterval(2*60*60);
	 * session.setAttribute("typelist", list); return "advertiseManage"; }
	 */
	
//	private ButtleController b = new ButtleController();
//	@Test
//	public void insert() {
//		Buttle_Adv butt=new Buttle_Adv();
//		Date date = new Date();
//		butt.setType(1);
//		butt.setUrl("http:www.baidu.com");
//		butt.setImg_Src("image/pic.png");
//		butt.setAlt("测试用例");
//		butt.setRemark("Remark");
//		butt.setCreate_Time(new Timestamp(date.getTime()));
//		butt.setOperator_Id(3);
//		butt.setStatus(1);
//		b.ButtleService1(butt);
//	}

	@RequestMapping("th01")
	public String th01(HttpServletRequest request) {
		List<AdvertiseType> list =advertiseTypeService.selAdvAll(request);
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(2*60*60);
		session.setAttribute("typelist", list);
		return "advertisement/index";
	}
	
	@RequestMapping("th02")
	public String th02(HttpServletRequest request) {
		List<AdvertiseType> list =advertiseTypeService.selAdvAll(request);
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(2*60*60);
		session.setAttribute("typelist", list);
		return "advertisement/table_jqgrid";
	}
	
	@RequestMapping("th03")
	public String th03() {
		return "boundary";
	}
	
	@RequestMapping("boottable")
	public String th03(HttpServletRequest request) {
		List<AdvertiseType> list =advertiseTypeService.selAdvAll(request);
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(2*60*60);
		session.setAttribute("typelist", list);
		return "table_adv";
	}
}
