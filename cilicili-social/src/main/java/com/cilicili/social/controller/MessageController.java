package com.cilicili.social.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cilicili.social.mapper.Tb_messageMapper;
import com.cilicili.social.mapper.Tb_u_usersMapper;
import com.cilicili.social.service.MessageService;
import com.cilicilil.domain.social.Message;
import com.cilicilil.domain.social.MessageBox;

@Controller
@RequestMapping("Message")
public class MessageController {
	@Resource 
	MessageService messageService;
	
	@Resource
	Tb_u_usersMapper tb_u_usersMapper;
	
	@Resource 
	Tb_messageMapper Tb_messageMapper;
	
	@RequestMapping("SendMessage")
	public String sendMessage(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userID =((String) session.getAttribute("userID")).trim();
		model.addAttribute("userID", userID);
		System.out.println(userID);
		return "social/message/sendMessage";
	}
	
	@RequestMapping("SendMessage.do")
	public String SendMessageDo(String title,String content,HttpServletRequest request,String recieve_userid){
		HttpSession session = request.getSession();
		String sent_userid=((String) session.getAttribute("userID")).trim();
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
	public String lookMessage(String message_ID,Model model,HttpServletRequest request) {
		Message message= messageService.selectByID(Integer.parseInt(message_ID));
		messageService.updateMessage(19);
		String sentUserName=messageService.selectUsernameByUserid(message.getSent_userid());
		String reveiceUserName = messageService.selectUsernameByUserid(message.getRecieve_userid());
		model.addAttribute("sentUserName", sentUserName);
		model.addAttribute("reveiceUserName", reveiceUserName);
		model.addAttribute("message", message);
		return "social/message/lookMessage";
	}
	
	@RequestMapping("reveiceBox")
	public String reveiceBox(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId=((String) session.getAttribute("userID")).trim();
		List<MessageBox> messageBoxList=messageService.selectReceive(userId);
		System.out.println("qqqqqqqqqqq");
		System.out.println(messageBoxList);
		System.out.println("qqqqqqqqqqq");
		String userName=messageService.selectUsernameByUserid(userId);
		model.addAttribute("messageBoxList", messageBoxList);
//		System.out.println("1111111111");
//		System.out.println(Tb_messageMapper.query1());
//		System.out.println("2222222222");

		
		return "social/message/receiveBox.html";
	}
	
	@RequestMapping("sendBox")
	public String sendBox(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId=((String) session.getAttribute("userID")).trim();
		List<MessageBox> messageBoxList=messageService.selectSend(userId);
		model.addAttribute("messageBoxList", messageBoxList);
		return "social/message/sendBox.html";
	}
	
	@RequestMapping("sendBoxDelete")
	@ResponseBody
	public String sendBoxDelete(String message_id) {
		messageService.sendBoxDelete(Integer.parseInt(message_id));
		return "<script>alert('删除成功!');location='sendBox'</script>";
	}
	
	@RequestMapping("reveiceBoxDelete")
	@ResponseBody
	public String reveiceBoxDelete(String message_id) {
		messageService.reveiceBoxDelete(Integer.parseInt(message_id));
		return "<script>alert('删除成功!');location='reveiceBox'</script>";
	}
}
