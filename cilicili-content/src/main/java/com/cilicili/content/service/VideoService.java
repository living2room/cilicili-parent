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

	/**
	 * 通过类型名拿到该类 审核未通过和未审核的视频信息
	 * 
	 * @param text
	 * @return
	 */
	public List<VideoReviewDto> getVideoByTypeName(String text) {
		List<VideoReviewDto> vrDtoList = new ArrayList<VideoReviewDto>();
		QueryWrapper<Type> queryWrapper = new QueryWrapper<Type>();
		queryWrapper.eq("type", text);
		Type type = typeMapper.selectOne(queryWrapper);

		QueryWrapper<VideoType> tqueryWrapper = new QueryWrapper<VideoType>();
		tqueryWrapper.eq("type_id", type.getId());
		List<VideoType> typetList = vTypeMapper.selectList(tqueryWrapper);
		for (int j = 0; j < typetList.size(); j++) {
			VideoReviewDto vrDto = new VideoReviewDto();
			QueryWrapper<VideoExamine> vequeryWrapper = new QueryWrapper<VideoExamine>();
			vequeryWrapper.eq("video_id", typetList.get(j).getVideoId());
			vequeryWrapper.ne("video_status", 1);
			VideoExamine videoExamine = veMapper.selectOne(vequeryWrapper);
			if (videoExamine != null) {
				VideoInfo byId = infoMapper
						.selectById(typetList.get(j).getVideoId());
				vrDto.setId(byId.getId());
				vrDto.setVideoName(byId.getVideoName());
				vrDto.setVideoUploadtime(byId.getVideoUploadTime());
				QueryWrapper<VideoPic> vpqueryWrapper = new QueryWrapper<VideoPic>();
				vpqueryWrapper.eq("video_id", byId.getId());
				vpqueryWrapper.eq("pic_type", 2);
				VideoPic videoPic = vPicMapper.selectOne(vpqueryWrapper);
				vrDto.setPicActualUrl(videoPic.getPicActualUrl());
				QueryWrapper<VideoUrl> vuqueryWrapper = new QueryWrapper<VideoUrl>();
				vuqueryWrapper.eq("video_id", byId.getId());
				VideoUrl videoUrl = vUrlMapper.selectOne(vuqueryWrapper);
				vrDto.setActualUrl(videoUrl.getActualUrl());
				vrDto.setVideoStatus(videoExamine.getVideoStatus());
				vrDtoList.add(vrDto);
			}
		}
		//
		return vrDtoList;
	}

	/** 添加视频相关信息
	 * @param videoInfoId 视频ID
	 * @param base64 二进制图片
	 * @param videoName 视频标题
	 * @param videoDescribe 视频描述
	 * @param tn 类型
	 */
	public void addvideoInfo(HttpSession session,String videoInfoId, String base64,
		String videoName, String videoDescribe, String t1,String tn) {
		VideoInfo entity = new VideoInfo();
		entity.setVideoDescribe(videoDescribe);
		entity.setVideoTitle(videoName);
		entity.setId(videoInfoId);
		infoMapper.updateById(entity );
		
		VideoType vType = new VideoType();
		vType.setVideoId(videoInfoId);
		vType.setTypeId(Integer.valueOf(tn));
		vType.setFatherType(Integer.valueOf(t1));
		vTypeMapper.insert(vType );
		
		Users user = (Users) session.getAttribute("user");
		//目前session里面没有User
		VideoUser uVi = new VideoUser();
		uVi.setUserId(user.getUserId());
		uVi.setVideoId(videoInfoId);
		vUserMapper.insert(uVi );
		
		VideoData vData = new VideoData();
		vData.setVideoId(videoInfoId);
		vData.setVideoPlayed(0L);
		vData.setLikedNum(0L);
		vData.setBulletScreenNum(0L);
		vDataMapper.insert(vData );
		
		// 将封面图片放进redis里
		redisUtil.set(videoInfoId,base64);
	}

	/**拿到该视频的信息
	 * @param videoPath
	 */
	public VideoDetail getThisVideo(String videoPath) {
		VideoDetail detail = new VideoDetail();
		QueryWrapper<VideoUrl> queryWrapper = new QueryWrapper<VideoUrl>();
		queryWrapper.eq("video_id", videoPath);
		VideoUrl videoUrl = vUrlMapper.selectOne(queryWrapper );
		VideoInfo videoInfo = infoMapper.selectById(videoUrl.getVideoId());
		
		String pic = (String)redisUtil.get(videoPath);
		
		QueryWrapper<VideoData> dqueryWrapper = new QueryWrapper<VideoData>();
		dqueryWrapper.eq("video_id", videoInfo.getId());
		VideoData vData = vDataMapper.selectOne(dqueryWrapper );
		vData.setVideoPlayed(vData.getVideoPlayed()+1);
//		vDataMapper.updateById(vData);
		QueryWrapper<VideoData> updateWrapper = new  QueryWrapper<VideoData>();
		updateWrapper.eq("video_id", vData.getVideoId());
		vDataMapper.update(vData, updateWrapper );
		detail.setActualUrl(videoUrl.getActualUrl());
		detail.setBulletScreenNum(vData.getBulletScreenNum());
		detail.setLikedNum(vData.getLikedNum());
		//detail.setPicUrl(pic);
		detail.setVideoDescribe(videoInfo.getVideoDescribe());
		detail.setVideoId(vData.getVideoId());
		detail.setVideoPlayed(vData.getVideoPlayed());
		detail.setVideoTitle(videoInfo.getVideoTitle());
		detail.setVideoUploadTime(videoInfo.getVideoUploadTime());
		return detail;
		
	}

	// /**分片处理待完成
	// * @param req
	// */
	// public void uploadByPieces(MultipartFile file,HttpServletRequest req) {
	//
	// int i = 1;
	// String localPath = req.getSession().getServletContext().getRealPath("")
	// + "temp" + File.separator + "video" + File.separator;
	// String localfile = i + "_" + file.getOriginalFilename();
	// File localFile = new File(localPath, localfile);
	// try {
	// // TODO 查看webloader上传参数制作Dto
	// localFile.getParentFile().mkdirs();
	// file.transferTo(localFile);
	// i++;
	// } catch (IllegalStateException | IOException e2) {
	// e2.printStackTrace();
	// }
	//
	// }

}