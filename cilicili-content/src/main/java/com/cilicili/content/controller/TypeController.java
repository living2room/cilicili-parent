/**
 * 
 */
package com.cilicili.content.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.content.service.TypeService;

/**
 * @author 李明睿
 * 2019年5月26日
 */
@Controller
@RequestMapping("type")
public class TypeController {
	@Resource
	private TypeService tService;
	
}
