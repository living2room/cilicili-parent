package com.cilicili.content.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.content.service.NotPassReasonService;

@Controller()
@RequestMapping("notPassReason")
public class NotPassReasonController {

	@Resource
	private NotPassReasonService nprService;
	
}
