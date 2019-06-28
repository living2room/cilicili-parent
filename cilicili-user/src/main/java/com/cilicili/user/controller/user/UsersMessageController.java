package com.cilicili.user.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cilicili.domain.user.user.UsersMessage;
import com.cilicili.user.service.impl.user.UsersMessageServiceImpl;

@Controller
@RequestMapping("/message")
public class UsersMessageController {

	@Resource
	private UsersMessageServiceImpl usersMessageServiceImpl;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@RequestMapping("/toUsersMessage")
	public String toUsersMessage() {

		return "redirect:/message/usersMessage";
	}
	/*
	 * 通过昵称查它的所有基本信息(usersMessage)
	 */
	@RequestMapping("/usersMessage")
	public String usersMessage(@RequestParam("userName") String userName, Model model,HttpServletRequest request) {

		try {
			UsersMessage usersMessage = this.usersMessageServiceImpl.findByUserName(userName);
			System.out.println(usersMessage);
			int age = usersMessage.getAge();
			String home = usersMessage.getHome();
			String phone = usersMessage.getPhone();
			String qq = usersMessage.getQq();
			int sex = usersMessage.getSex();

			if (age == 0) {
				model.addAttribute("age", "无");
			} else {
				model.addAttribute("age", age);
			}

			// String imgUrl = usersMessage.getImgUrl();
			String path = redisTemplate.opsForValue().get(userName);
			//String path = System.getProperty(userName);
			
			
			
			//头像
			HttpSession session = request.getSession();
			session.setAttribute("url1", path);
			
			//model.addAttribute("url1", path);

			model.addAttribute("userName", userName);
			model.addAttribute("home", home);
			model.addAttribute("phone", phone);
			model.addAttribute("qq", qq);
			model.addAttribute("sex", sex);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return "user/usersMessage";
	}
	// 更改用户基本信息
	@RequestMapping("/upUsersMessage")
	@ResponseBody
	public Map<String,Object> updateUsersMessage(UsersMessage usersMessage, Model model,
			String base64/* , @RequestParam("file") MultipartFile file */) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		// 把每个用户的头像地址存到项目里面去
		String userName = usersMessage.getUserName();
		if (base64 != null & base64 != "") {
			redisTemplate.opsForValue().set(userName, base64);
			//System.setProperty(userName, base64);
		}	
		int count = this.usersMessageServiceImpl.upUsersMessage(usersMessage);
		if(count > 0 ) {
			map.put("msg", 1);
		}else {
			map.put("msg", 0);
		}
		return map;
	}
}
