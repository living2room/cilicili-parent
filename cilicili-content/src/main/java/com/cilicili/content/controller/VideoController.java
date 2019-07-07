/**
 *
 */
package com.cilicili.content.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cilicili.common.dto.VideoReviewDto;
import com.cilicili.content.service.BulletScreenService;
import com.cilicili.content.service.VideoExamineService;
import com.cilicili.content.service.VideoService;
import com.cilicili.domain.content.BulletScreen;
import com.cilicili.domain.content.VideoExamine;
import com.cilicili.domain.user.user.Users;


/**
 * @author 李明睿 2019年5月23日
 */
@RestController
@RequestMapping("v")
public class VideoController {

	@Resource
	private VideoService vService;
	@Resource
	private BulletScreenService bsService;


	@Resource VideoExamineService veService;

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


	/**点击某类型后调用的Controller
	 * @param text 类型名称
	 * @param model
	 * @return
	 */
	@PostMapping("get/{text}")
	@ResponseBody()
	public String getVideoByName(@PathVariable String text, Model model) {
		List<VideoReviewDto> videoByTypeName = vService.getVideoByTypeName(text);
		model.addAttribute("list",videoByTypeName);
		String videoJson = JSONArray.toJSONStringWithDateFormat(videoByTypeName, " yyyy-MM-dd HH:mm:ss");
		return videoJson;
	}



	/**获取该视频的弹幕
	 * @param vi 视频id
	 * @return
	 */

	@GetMapping(value="/bs/{vi}",produces = {"application/json;charset=UTF-8"})
	public String getvideoBs(@PathVariable("vi") String vi) {
		List<BulletScreen> bulletScreen = bsService.getBulletScreen(vi);
		return JSON.toJSONString(bulletScreen);
	}
	@GetMapping(value="/bs/get/{vi}",produces = {"application/json;charset=UTF-8"})
	public String getvideoBsList(@PathVariable("vi") String vi) {
		List<BulletScreen> bulletScreen = bsService.getBulletScreen(vi);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "1");
		map.put("danmaku", bulletScreen);
		return JSON.toJSONString(map);
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
		return  "1";
	}


	@PostMapping("videoVerity")
	@ResponseBody()
	public String videoVerity(@PathVariable String ispass,@PathVariable String reason,@PathVariable String videoid, Model model) {
		if(ispass.equals("1")){
			VideoExamine ve = veService.selectOne(videoid);
			ve.setVideoApprovalTime(new Date());
			ve.setVideoStatus(1);
		}else if(ispass.equals("-1")) {
			VideoExamine ve = veService.selectOne(videoid);
			ve.setVideoLastExamineTime(new Date());
			ve.setReasonId(Integer.parseInt(reason));
			ve.setVideoStatus(-1);
		}
		return "success";
	}
	@PostMapping("/send/{vid}")
	public String sbSend(@PathVariable("vid")String vid,HttpSession session,String time,String text,String color,Integer type) {
		Object attribute = session.getAttribute("user");
		if (attribute instanceof Users && attribute != null) {
			Users user = (Users) attribute;
			bsService.insertBs(user,vid, time, text, color, type);
			return "1";
		}else {
			//未登录
			return "0";
		}
	}
}
