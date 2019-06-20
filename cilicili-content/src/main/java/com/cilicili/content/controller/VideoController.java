/**
 * 
 */
package com.cilicili.content.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cilicili.bean.content.Type;
import com.cilicili.common.dto.UploadJsonObj;
import com.cilicili.common.dto.VideoReviewDto;
import com.cilicili.content.service.VideoService;

/**
 * @author 李明睿 2019年5月23日
 */
@RestController
@RequestMapping("v")
public class VideoController {

	
	@Resource
	private VideoService vService;
	
	/**点击上传 分片传到临时位置并保存为一个文件
	 */
	
	
	@PostMapping("up")
	public String uploadByPieces(HttpServletRequest req,HttpSession session) {
		File file = vService.videoupload(req);
		Object obj = vService.addvideoDb(file, session);
		if (obj instanceof String) {
			String id = (String) obj;
			return "success "+id;
		}
		return "failed";
	}
	/**
	 * 上传成后回调方法
	 */
	@PostMapping("afterup")
	public void uploadSuccess() {
	}
	
	@PostMapping("picThumb")
	public void picThumb(String src) {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	}
	
	
	/**点击某类型后调用的Controller
	 * @param text 类型名称
	 * @param model
	 * @return
	 */
	@PostMapping("get/{text}")
	public String getVideoByName(@PathVariable String text, Model model) {
		List<VideoReviewDto> videoByTypeName = vService.getVideoByTypeName(text);
		System.out.println(videoByTypeName);
		model.addAttribute("list",videoByTypeName);
		return "success";
	}
	
	
}
