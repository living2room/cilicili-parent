/**
 * 
 */
package com.cilicili.content.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cilicili.content.service.VideoService;

/**
 * @author 李明睿 2019年5月23日
 */
@Controller
@RequestMapping("v")
public class VideoController {

	@Resource
	private VideoService vService;
	@RequestMapping(value = "up", method = RequestMethod.POST)
	public void upload(HttpServletRequest req, HttpServletResponse resp) {
		vService.videoProcessor(req,resp);
//		System.out.println("拿到视频！");
//		MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
//		Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
//		String upaloadUrl = req.getSession().getServletContext()
//				.getRealPath("/") + "upload/";// 得到当前工程路径拼接上文件名
//		File dir = new File(upaloadUrl);
//		System.out.println(upaloadUrl);
//		if (!dir.exists())// 目录不存在则创建
//			dir.mkdirs();
//		for (MultipartFile file : files.values()) {
//			String fileName = file.getOriginalFilename();
//			System.out.println(fileName);
//			File tagetFile = new File(upaloadUrl + fileName);// 创建文件对象
//			if (!tagetFile.exists()) {// 文件名不存在 则新建文件，并将文件复制到新建文件中
//				try {
//					tagetFile.createNewFile();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				try {
//					file.transferTo(tagetFile);
//				} catch (IllegalStateException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//
//			}
//		}
//		System.out.println("接收完毕");
	}
}
