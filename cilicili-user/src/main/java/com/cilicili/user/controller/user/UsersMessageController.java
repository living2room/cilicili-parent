package com.cilicili.user.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cilicili.common.utils.RedisUtil;
import com.cilicili.domain.user.user.UsersMessage;
import com.cilicili.user.service.impl.user.UsersMessageServiceImpl;

@Controller
@RequestMapping("/message")
public class UsersMessageController {

	@Resource
	private UsersMessageServiceImpl usersMessageServiceImpl;
	@Resource
	private RedisUtil redisUtil;

	@RequestMapping("/toUsersMessage")
	public String toUsersMessage() {

		return "redirect:/message/usersMessage";
	}

	/* 图片编辑器的测试 */
	@RequestMapping("/toindex")
	public String toindex() {

		return "upload-cropped-image-to-server";
	}

	/*
	 * 通过昵称查它的所有基本信息(usersMessage)
	 */
	@RequestMapping("/usersMessage")
	public String usersMessage(@RequestParam("userName") String userName, Model model) {

		try {
			UsersMessage usersMessage = this.usersMessageServiceImpl.findByUserName(userName);
			System.out.println(usersMessage);
			int age = usersMessage.getAge();
			String home = usersMessage.getHome();
			// String idCard = usersMessage.getIdCard();
			// String imgUrl = usersMessage.getImgUrl();
			String phone = usersMessage.getPhone();
			String qq = usersMessage.getQq();
			int sex = usersMessage.getSex();

			if (age == 0) {
				model.addAttribute("age", "无");
			} else {
				model.addAttribute("age", age);
			}

			// String imgUrl = usersMessage.getImgUrl();
			// Date createTime = usersMessage.getCreateTime();
			// Date lastLoginTime = usersMessage.getLastLoginTime();

			// String path = redisUtil.get(userName);
			String path = System.getProperty(userName);
			//System.out.println(path + ":pathhhhhh");
			model.addAttribute("url1", path);

			model.addAttribute("userName", userName);
			model.addAttribute("home", home);
			// model.addAttribute("idCard", idCard);
			// model.addAttribute("url1", imgUrl);
			model.addAttribute("phone", phone);
			model.addAttribute("qq", qq);
			model.addAttribute("sex", sex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "usersMessage";
	}

	/*
	 * // 更改用户基本信息
	 * 
	 * @RequestMapping("/upUsersMessage") public String
	 * updateUsersMessage(UsersMessage usersMessage, Model model, String
	 * base64, @RequestParam("file") MultipartFile file) { // 上传
	 * System.out.println("111" + base64); System.out.println("111" + usersMessage);
	 * // 把每个用户的头像地址存到项目里面去 String userName = usersMessage.getUserName(); if (base64
	 * != null & base64 != "") { // redisUtil.set(userName,base64);
	 * System.setProperty(userName, base64); // String path =
	 * redisUtil.get(userName); String path = System.getProperty(userName);
	 * 
	 * System.out.println(path + ":02path"); model.addAttribute("url1", path); } int
	 * count = this.usersMessageServiceImpl.upUsersMessage(usersMessage); // 更改后给它显示
	 * usersMessage(usersMessage.getUserName(), model);
	 * 
	 * return "usersMessage"; }
	 */
	
	// 更改用户基本信息
	@RequestMapping("/upUsersMessage")
	@ResponseBody
	public Map<String,Object> updateUsersMessage(UsersMessage usersMessage, Model model,
			String base64/* , @RequestParam("file") MultipartFile file */) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		//验证
		/*
		 * usersMessage.getAge(); usersMessage.getPhone(); usersMessage.getQq();
		 */
		// 上传
		// 把每个用户的头像地址存到项目里面去
		String userName = usersMessage.getUserName();
		if (base64 != null & base64 != "") {
			// redisUtil.set(userName,base64);
			System.setProperty(userName, base64);
			// String path = redisUtil.get(userName);
			String path = System.getProperty(userName);

			System.out.println(path + ":02path");
			//model.addAttribute("url1", path);
		}
		
		int count = this.usersMessageServiceImpl.upUsersMessage(usersMessage);
		// 更改后给它显示
		//usersMessage(usersMessage.getUserName(), model);
		if(count > 0 ) {
			map.put("msg", 1);
		}else {
			map.put("msg", 0);
		}

		return map;
	}

}
