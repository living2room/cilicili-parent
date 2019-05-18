package com.cilicili.content.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cilicili.content.service.PageService;
/**
 * 页面导航的Controller
 * @author  李明睿
 *
 */
@Controller
@RequestMapping("/to")
public class PageController {
//
//	/**
//	 * 去一级主页
//	 * 
//	 * @return
//	 */
//	@RequestMapping("hone")
//	public String toHomePage() {
//		return "";
//	}
//
//	/**
//	 * 去二级主页
//	 * 
//	 * @param url
//	 * @return
//	 */
//	@RequestMapping(value = "{url}", method = RequestMethod.GET)
//	public String toSecHome(@PathVariable("url") String url) {
//		return "";
//	}
//
//	/**
//	 * 去三级主页
//	 * 
//	 * @param securl
//	 * @param thirdurl
//	 * @return
//	 */
//	@RequestMapping(value = "{securl}/{thirdurl}", method = RequestMethod.GET)
//	public String tothirdHome(@PathVariable("securl") String securl, @PathVariable("thirdurl") String thirdurl) {
//		return "";
//	}
//
//	/**
//	 * 去某个视频页面
//	 * 
//	 * @param video的request URL
//	 * @return
//	 */
//	@RequestMapping(value = "v/{video}", method = RequestMethod.GET)
//	public String toVideoPage(@PathVariable("video") String video) {
//		return "";
//	}
//
//	/**
//	 * 拿到远端IP地址
//	 * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
//	 * X-Forwarded-For中第一个非unknown的有效IP字符串为用户的真实IP地址
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("iptest")
//	@ResponseBody
//	public String ipTest(HttpServletRequest request) {
//		String removeip = "";
//		if (request.getHeader("x-forwarded-for") == null) {
//			removeip = request.getRemoteAddr();
//			String remoteHost = request.getRemoteHost();
//			int remotePort = request.getRemotePort();
//			String remoteUser = request.getRemoteUser();
//			System.out.println(remoteHost+"+" + remotePort +" +" + remoteUser);
//		}else {
//			removeip = request.getHeader("x-forwarded-for");
//		}
//		return removeip;
//	}
	@Resource
	PageService pService;

	@RequestMapping("{id}")
	@ResponseBody
	public int typetest(@PathVariable("id")Integer id) {
		int i = pService.type(id);
		return i;
	}
}
