package com.cilicili.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.comment.service.DeleteLogService;

@Controller
@RequestMapping("/")
public class DeleteLogController {
	
	@Autowired
	private DeleteLogService deleteLogService;

	//@RequestMapping("/")
	/**
	 * 删除日志文件
	 * @param 
	 * @return 
	 */
	//public String 
}
