package com.cilicili.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.comment.domain.Reply;
import com.cilicili.comment.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	/*
	 * @Autowired private ReplyService replyService;
	 * 
	 * @RequestMapping("/addReply") public String addReply(Reply reply){
	 * System.out.println("回复信息："+reply.toString()); if
	 * (reply.getContent()!=null&&reply.equals("")){ replyService.insert(reply); }
	 * return ""; // return
	 * "redirect:/user/comment?contentId="+reply.getrContentid(); }
	 */
}
