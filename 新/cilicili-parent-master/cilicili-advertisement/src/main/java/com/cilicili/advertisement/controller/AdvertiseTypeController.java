package com.cilicili.advertisement.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.advertisement.service.AdvertiseTypeService;
import com.cilicili.domain.advertisement.AdvertiseType;
import com.cilicili.domain.advertisement.ListSetClass;

@Controller("AdvertiseTypeCon")
@RequestMapping("/advertisetype")
public class AdvertiseTypeController {

	@Resource
	private AdvertiseTypeService advertiseTypeService;

	/*
	 * @RequestMapping("into") public String intocenter() {
	 * System.out.println("2222244444");
	 * 
	 * return "advertiseManage"; }
	 */

	@RequestMapping("pub")
	public String publish(HttpServletRequest request) {
		System.out.println("2222244444");
		return "advertisement/publish";
	}
	/*
	 * @RequestMapping("selectall") public String selectall(HttpServletRequest
	 * request) {
	 * 
	 * List<AdvertiseType> selAdvAll = advertiseTypeService.selAdvAll(); for
	 * (AdvertiseType centerAdv : selAdvAll) { System.out.println(centerAdv); }
	 * 
	 * //ListSetClass typelist = advertiseTypeService.selAdvAll(request);
	 * System.out.println("#####################################"); HttpSession
	 * session = request.getSession(); session.setMaxInactiveInterval(24*60*60);
	 * List<AdvertiseType> AdvList = (List<AdvertiseType>)
	 * session.getAttribute("typelist"); for (AdvertiseType advertiseType : AdvList)
	 * { System.out.println(advertiseType); }
	 * 
	 * for (AdvertiseType advertise : typelist.getTypelist()) {
	 * System.out.println(advertise); }
	 * 
	 * return "hello"; }
	 */

}
