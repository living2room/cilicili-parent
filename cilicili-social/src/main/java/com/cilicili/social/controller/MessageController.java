package com.cilicili.social.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cilicili.social.domain.Message;
import com.cilicili.social.domain.MessageBox;
import com.cilicili.social.mapper.Tb_u_usersMapper;
import com.cilicili.social.service.MessageService;

@Controller
@RequestMapping("Message")
public class MessageController {
	@Resource 
	MessageService messageService;
	
	@Resource
	Tb_u_usersMapper tb_u_usersMapper;
	
	@RequestMapping("SendMessage")
	public String sendMessage(String userID,Model model) {
		model.addAttribute("userID", userID);
		System.out.println(userID);
		return "social/message/sendMessage";
	}
	
	@RequestMapping("SendMessage.do")
	public String SendMessageDo(String title,String content,String sent_userid,String recieve_userid){
		sent_userid="11111111aassddffgghhjjklghfghfgd";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("【yyyy年MM月dd日HH时mm分】");
		String createdate = sdf.format(date);
		messageService.sendMessage(createdate+title, content, sent_userid, recieve_userid);
		return "social/message/alert.html";
	}
	
	@RequestMapping("findByUserName")
	@ResponseBody
	public String findByUserName(String userName) {
		System.out.println(userName);
		return messageService.findByUserName(userName);
	}
	
	@RequestMapping("lookMessage")
	public String lookMessage(Model model) {
		Message message= messageService.selectByID(19);
		messageService.updateMessage(19);
		String sentUserName=messageService.selectUsernameByUserid(message.getSent_userid());
		String reveiceUserName = messageService.selectUsernameByUserid(message.getRecieve_userid());
		model.addAttribute("sentUserName", sentUserName);
		model.addAttribute("reveiceUserName", reveiceUserName);
		model.addAttribute("message", message);
		return "social/message/lookMessage";
	}
	
	@RequestMapping("reveiceBox")
	public String reveiceBox(Model model) {
		String userId="11111111aassddffgghhjjklghfghfgd";
		List<MessageBox> messageBoxList=messageService.selectReceive(userId);
		//String userName=messageService.selectUsernameByUserid();
		model.addAttribute("messageBoxList", messageBoxList);
		return "social/message/receiveBox.html";
	}
	
	@RequestMapping("sendBox")
	public String sendBox(Model model) {
		String userId="11111111aassddffgghhjjklghfghfgd";
		List<MessageBox> messageBoxList=messageService.selectSend(userId);
		model.addAttribute("messageBoxList", messageBoxList);
		return "social/message/sendBox.html";
	}
	
	@RequestMapping("sendBoxDelete")
	@ResponseBody
	public String sendBoxDelete(String message_id) {
		messageService.sendBoxDelete(Integer.parseInt(message_id));
		return "1";
	}
	
	@RequestMapping("reveiceBoxDelete")
	@ResponseBody
	public String reveiceBoxDelete(String message_id) {
		messageService.reveiceBoxDelete(Integer.parseInt(message_id));
		return "1";
	}
}
