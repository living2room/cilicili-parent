/**
 * 
 */
package com.cilicili.content.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cilicili.bean.content.Type;
import com.cilicili.common.dto.TypeTreeJsonObj;
import com.cilicili.content.service.TypeService;

/**
 * @author 李明睿
 * 2019年5月26日
 */
@RestController
@RequestMapping("t")
public class TypeController {
	@Resource
	private TypeService tService;
	
	@PostMapping("get")
	public String getTyleJsonList() {
		List<TypeTreeJsonObj> types = tService.getTypes();
		return JSON.toJSONString(types);
	}
	

	@PostMapping("update")
	public String formupdate(Type type) {
		int i = tService.editType(type);
		if (i == 1) {
			return com.cilicili.common.msg.ReturnMsg.SUCCESS;
		}
		return com.cilicili.common.msg.ReturnMsg.FAILED;
	}
	
	@PostMapping("new")
	public String formnew(Type type) {
		int i = tService.addType(type);
		if (i == 1) {
			return com.cilicili.common.msg.ReturnMsg.SUCCESS;
		}
		return com.cilicili.common.msg.ReturnMsg.FAILED;
	}
	@PostMapping("del/{text}")
	public String delNode(@PathVariable String text) {
		int i  = tService.deleteTypeByName(text);
		if (i == 1) {
			return com.cilicili.common.msg.ReturnMsg.SUCCESS;
		}
		return com.cilicili.common.msg.ReturnMsg.FAILED;
	}
	
	@PostMapping("linkage/{rating}/{id}")
	private String gettypeByrating(@PathVariable("rating") String rating, @PathVariable("id") String id,Model model) {
		List<Type> byRatingAndId = tService.getTypeByRatingAndId(Integer.valueOf(id),Integer.valueOf(rating));
		return JSON.toJSONString(byRatingAndId);
	}
}
