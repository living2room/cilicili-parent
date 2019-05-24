/**
 * 
 */
package com.cilicili.content.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cilicili.bean.content.Type;
import com.cilicili.bean.content.Users;
import com.cilicili.bean.content.VideoInfo;
import com.cilicili.bean.content.VideoPic;
import com.cilicili.bean.content.VideoType;
import com.cilicili.bean.content.VideoUrl;
import com.cilicili.bean.content.VideoUser;
import com.cilicili.common.dto.UploadJsonObj;
import com.cilicili.common.utils.PictureMerge;
import com.cilicili.common.utils.VideoResolution;
import com.cilicili.content.mapper.TypeMapper;
import com.cilicili.content.mapper.VideoInfoMapper;
import com.cilicili.content.mapper.VideoPicMapper;
import com.cilicili.content.mapper.VideoTypeMapper;
import com.cilicili.content.mapper.VideoUrlMapper;
import com.cilicili.content.mapper.VideoUserMapper;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @author 李明睿 2019年5月23日
 */
@Service
public class VideoService {

	@Resource
	private VideoInfoMapper infoMapper;
	@Resource
	private VideoTypeMapper vTypeMapper;
	@Resource
	private VideoUrlMapper vUrlMapper;
	@Resource
	private VideoUserMapper vUserMapper;
	@Resource 
	private TypeMapper typeMapper;
	@Resource
	private VideoPicMapper vPicMapper;
	/**
	 * 处理用户上传的视频
	 * 
	 * @param req
	 * @param resp
	 */
	public void addvideoProcessor(HttpServletRequest req, HttpServletResponse resp,
			UploadJsonObj jsonObj) {

		MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
		String upaloadUrl = req.getSession().getServletContext()
				.getRealPath("/") + "upload/";// 得到当前工程路径拼接上文件名
		File dir = new File(upaloadUrl);
		Snowflake snowflake = IdUtil.createSnowflake(1, 1);
		System.out.println(upaloadUrl);
		if (!dir.exists())// 目录不存在则创建
			dir.mkdirs();
		for (MultipartFile file : files.values()) {
			String fileName = file.getOriginalFilename();
			File toFile;
			String uuid = "";
			VideoInfo entity = new VideoInfo();
			try {
				toFile = VideoResolution.multipartFileToFile(file);
				Map<String, Object> videoInfo = VideoResolution
						.getVideoInfo(toFile);
				String nextId = snowflake.nextIdStr();
				entity.setId(nextId);
				entity.setVideoDuration((long) videoInfo.get("Duration"));
				entity.setVideoFormat((String) videoInfo.get("Format"));
				entity.setVideoFrames((long) videoInfo.get("FrameLength"));
				entity.setVideoName((String) videoInfo.get("VideoName"));
				entity.setVideoSize((String) videoInfo.get("Size"));
				entity.setVideoUploadTime(DateUtil.date());
				entity.setVideoIsAvailable(0);
				entity.setIsVip(jsonObj.getVip());
				entity.setVideoTitle(jsonObj.getTitle());
				entity.setVideoDescribe(jsonObj.getDescribe());
				int i = infoMapper.insert(entity);
				addthumbnail(toFile,entity);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println(fileName);
			File tagetFile = new File(upaloadUrl + fileName);// 创建文件对象
			if (!tagetFile.exists()) {// 文件名不存在 则新建文件，并将文件复制到新建文件中
				try {
					VideoUrl videoUrl = new VideoUrl();
					long id = snowflake.nextId();
					videoUrl.setId(id);
					videoUrl.setVideoId(entity);
					videoUrl.setActualUrl(upaloadUrl);
					videoUrl.setRequestUrl(String.valueOf(id));
					int j = vUrlMapper.insert(videoUrl);
					tagetFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					
					VideoUser videoUser = new VideoUser();
					HttpSession session = req.getSession();
					Users user = (Users) session.getAttribute("user");
					long id = snowflake.nextId();
					videoUser.setId(id);
					videoUser.setUserId(user);
					videoUser.setVideoId(entity);
					videoUser.setIsVip(jsonObj.getVip());
					int k = vUserMapper.insert(videoUser);
					
					VideoType videoType = new VideoType();
					id = snowflake.nextId();
					videoType.setId(id);
					Type type = new Type();
					type.setId(jsonObj.getTypeId());
					videoType.setTypeId(type);
					videoType.setVideoId(entity);
					int l = vTypeMapper.insert(videoType);
					
					file.transferTo(tagetFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		System.out.println("接收完毕");
	}

	/**
	 * 获得视频所有类型
	 */
	public List<Type> getVideoType() {
		return typeMapper.selectList(null);
	}


	/**
	 * 视频拆分成帧
	 * 
	 */
	public void addthumbnail(File toFile, VideoInfo videoInfo) {
		// TODO 图片服务器
		String picPath= IdUtil.simpleUUID()+"jpg";
		PictureMerge tm = new PictureMerge();
		int n = 10;
		Long frames = videoInfo.getVideoFrames()/n;
		List<BufferedImage> bufferedImageList = new ArrayList<BufferedImage>();
		for (int i = 0; i < n-1; i++) {
			VideoResolution.getVideoPic(toFile, picPath, frames);
			frames  =frames + frames;
			bufferedImageList.add(tm.loadImageLocal(picPath));
		}
		// TODO 新图片地址
		String newPicPath = "";
		tm.writeImageLocal(newPicPath , tm.Merge(PictureMerge.orientation, bufferedImageList));
		
		VideoPic videoPic = new VideoPic();
		Snowflake snowflake = IdUtil.createSnowflake(1, 1);
		long id = snowflake.nextId();
		videoPic.setId(id);
		videoPic.setVideoId(videoInfo);
		videoPic.setPicActualUrl(newPicPath);
		videoPic.setPicRequestUrl(videoInfo.getId());
		vPicMapper.insert(videoPic );
	}
}