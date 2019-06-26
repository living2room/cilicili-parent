/**
 * 
 */
package com.cilicili.content.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/v/player/{vi}")
	public String getvidoe(@PathVariable("vi") String vi) {
		System.out.println();
		return vi;
	}
	@PostMapping("upinfo/{videoInfoId}")
	public String uploadInfo(HttpSession session,Model model, @PathVariable("videoInfoId")String videoInfoId, 
			String base64, String videoName, String videoDescribe,String t1, String t2,String t3) {
		if (t3 !=  null ) {
			vService.addvideoInfo(session,videoInfoId, base64, videoName, videoDescribe,t3);
		}else if(t2 != null) {
			vService.addvideoInfo(session,videoInfoId, base64, videoName, videoDescribe,t2);
		}else if(t1 != null){
			vService.addvideoInfo(session,videoInfoId, base64, videoName, videoDescribe,t1);
		}
		
		
		// 根据videoInfoId获取相应的封面图
//		String path = redisUtil.get(videoInfoId).toString();
		return  "";
	}
	
}
