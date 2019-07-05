/**
 * 
 */
package com.cilicili.content.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cilicili.bean.content.Type;
import com.cilicili.common.dto.UploadJsonObj;
import com.cilicili.content.service.VideoService;

/**
 * @author 李明睿 2019年5月23日
 */
@Controller
@RequestMapping("v")
public class VideoController {

	@Resource
	private VideoService vService;
	/**点击上传
	 * @param req
	 * @param resp
	 * @param jsonObj
	 */
	@RequestMapping(value = "up", method = RequestMethod.POST)
	public void upload(HttpServletRequest req, HttpServletResponse resp, UploadJsonObj jsonObj) {
		List<Type> videoType = vService.getVideoType();
		HttpSession session = req.getSession();
		session.setAttribute("type",videoType );
		vService.addvideoProcessor(req,resp,jsonObj);
	}
	

	@GetMapping("/v/player/{vi}")
	public String getvidoe(@PathVariable("vi") String vi) {
		System.out.println();
		return vi;
	}
	@PostMapping("upinfo/{videoInfoId}")
	public String uploadInfo(HttpSession session,Model model, @PathVariable("videoInfoId")String videoInfoId, 
			String base64, String videoName, String videoDescribe,String t1, String t2,String t3) {
		if (t3 !=  null ) {
			vService.addvideoInfo(session,videoInfoId, base64, videoName, videoDescribe,t1,t3);
		}else if(t2 != null) {
			vService.addvideoInfo(session,videoInfoId, base64, videoName, videoDescribe,t1,t2);
		}else if(t1 != null){
			vService.addvideoInfo(session,videoInfoId, base64, videoName, videoDescribe,t1,t1);
		}
		
		
		// 根据videoInfoId获取相应的封面图
//		String path = redisUtil.get(videoInfoId).toString();
		return  "";
	}
	
}
