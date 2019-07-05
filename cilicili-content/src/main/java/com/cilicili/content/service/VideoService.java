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
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cilicili.common.dto.VideoDetail;
import com.cilicili.common.dto.VideoReviewDto;
import com.cilicili.common.utils.PictureMerge;
import com.cilicili.common.utils.RedisUtil;
import com.cilicili.common.utils.VideoResolution;
import com.cilicili.content.mapper.TypeMapper;
import com.cilicili.content.mapper.VideoDataMapper;
import com.cilicili.content.mapper.VideoExamineMapper;
import com.cilicili.content.mapper.VideoInfoMapper;
import com.cilicili.content.mapper.VideoPicMapper;
import com.cilicili.content.mapper.VideoTypeMapper;
import com.cilicili.content.mapper.VideoUrlMapper;
import com.cilicili.content.mapper.VideoUserMapper;
import com.cilicili.domain.content.Type;
import com.cilicili.domain.content.VideoData;
import com.cilicili.domain.content.VideoExamine;
import com.cilicili.domain.content.VideoInfo;
import com.cilicili.domain.content.VideoPic;
import com.cilicili.domain.content.VideoType;
import com.cilicili.domain.content.VideoUrl;
import com.cilicili.domain.content.VideoUser;
import com.cilicili.domain.user.user.Users;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.ftp.Ftp;
/**
 * @author 李明睿 2019年5月23日
 */
@Service
public class VideoService {
	@Resource
	private RedisUtil redisUtil;
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
	@Resource
	private VideoExamineMapper veMapper;
	@Resource
	private VideoDataMapper vDataMapper;

	@Value("${FTP.ADDRESS}")
	private String ftpAddr;
	@Value("${FTP.PORT}")
	private int ftpport;
	@Value("${FTP.USERNAME}")
	private String username;
	@Value("${FTP.PASSWORD}")
	private String password;
	@Value("${FTP.BASE_PATH}")
	private String ftpPath;
	@Value("${FTP.REQUEST.PATH}")
	private String reqPath;

	/**
	 * 用户上传的视频 WebUploader 转到FTP
	 * 
	 * @param req
	 */
	public File videoupload(HttpServletRequest req) {
		FTPClient c = new FTPClient();
		String filePath ="/data/video";
		MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
		Ftp ftp = new Ftp(ftpAddr, ftpport);
		ftp.setMode(cn.hutool.extra.ftp.FtpMode.Active);
		String localPath = req.getSession().getServletContext().getRealPath("")
				+ "temp" + File.separator + "video" + File.separator;
		File localFile = new File(localPath);
		for (MultipartFile file : files.values()) {
			String localfile = file.getOriginalFilename();
			localFile = new File(localPath, localfile);
			try {
				localFile.getParentFile().mkdirs();
				file.transferTo(localFile);
				boolean d = ftp.cd(filePath);
				boolean b = ftp.upload(filePath, localFile);
					System.out.println(b+""+d);
			} catch (IllegalStateException | IOException e2) {
				e2.printStackTrace();
			}
		}
		// 关闭连接
		try {
			ftp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return localFile;
	}

	/**
	 * 添加信息到数据库
	 * 
	 * @param jsonObj
	 * @param localFile
	 */
	public Object addvideoDb(File localFile, HttpSession session) {
		Snowflake snowflake = IdUtil.createSnowflake(1, 1);
		String nextId = snowflake.nextIdStr();
		VideoInfo entity = new VideoInfo();
		String fileName = "";
		int i = 0;
		try {
			fileName = IdUtil.simpleUUID() + "."
					+ VideoResolution.getVideoFormat(localFile);
			// 上传本地文件
			Map<String, Object> videoInfo = VideoResolution
					.getVideoInfo(localFile);
			entity.setId(nextId);
			entity.setVideoDuration((long) videoInfo.get("Duration(s)"));
			entity.setVideoFormat((String) videoInfo.get("Format"));
			entity.setVideoFrames((long) videoInfo.get("FrameLength"));
			entity.setVideoName((String) videoInfo.get("VideoName"));
			entity.setVideoSize((String) videoInfo.get("Size"));
			entity.setVideoUploadTime(DateUtil.date());
			entity.setVideoIsAvailable(1);
			i = infoMapper.insert(entity);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println(fileName);
		VideoUrl videoUrl = new VideoUrl();
		long id = snowflake.nextId();
		videoUrl.setId(id);
		videoUrl.setVideoId(nextId);
		videoUrl.setActualUrl(ftpPath+"data/video/"+localFile.getName());//存放问题
		videoUrl.setRequestUrl(reqPath+"data/video/"+localFile.getName());
		int j = vUrlMapper.insert(videoUrl);

		VideoExamine videoExam = new VideoExamine();
		videoExam.setVideoId(nextId);
		videoExam.setVideoStatus(0);
		int n = veMapper.insert(videoExam);
		
		if (2 == i + j) {
			System.out.println("接收完毕");
			return nextId;
		}
		return false;
	}

	/**
	 * 制预览图
	 * 
	 * @param toFile 视频文件
	 * @param req
	 * @return 图片路径
	 */
	public String previewupload(String id, HttpServletRequest req) {
		 QueryWrapper<VideoUrl> queryWrapper = new  QueryWrapper<VideoUrl>();
		 queryWrapper.eq("video_id", id);
		VideoUrl videoUrl = vUrlMapper.selectOne(queryWrapper);
		 String actualUrl = videoUrl.getActualUrl();
		 
		String localPath = req.getSession().getServletContext().getRealPath("")
				+ "temp" + File.separator + "thumbnail"
				+ File.separator;
		Ftp ftp = new Ftp(ftpAddr, ftpport);
		ftp.setMode(cn.hutool.extra.ftp.FtpMode.Active);
		String outFile = req.getSession().getServletContext().getRealPath("")
				+ "temp" + File.separator + "video"
				+ File.separator;
		File file = new File(outFile);
		file.mkdir();
		ftp.download(actualUrl, file );
		VideoResolution.getVideoMiddlePic(file, localPath);
		return localPath;
	}
	/**
	 * 预览图插入数据库
	 * 
	 * @param pic
	 * @param infoId
	 */
	public void addpreviewDb(String pic, String infoId) {
		String picPath = "/data/imgs/preview/"+ pic;
		VideoInfo entity = infoMapper.selectById(infoId);
		VideoPic videoPic = new VideoPic();
		Snowflake snowflake = IdUtil.createSnowflake(1, 1);
		Long id = snowflake.nextId();
		videoPic.setId(id);
		videoPic.setVideoId(entity.getId());
		videoPic.setPicActualUrl(picPath);
		videoPic.setPicRequestUrl(entity.getId());
		videoPic.setPicType(2);
		vPicMapper.insert(videoPic);

	}

	/**
	 * 获得视频所有类型
	 */
	public List<Type> getVideoType() {
		return typeMapper.selectList(null);
	}

	/**制作缩略图上传
	 * @param toFile 视频文件
	 * @param req
	 * @param infoId 
	 * @return 图片的路径
	 */
	public String thumbnailupload(File toFile, HttpServletRequest req,
			String infoId) {
		Ftp ftp = new Ftp(ftpAddr, ftpport);
		ftp.setMode(cn.hutool.extra.ftp.FtpMode.Active);
		VideoInfo videoInfo = infoMapper.selectById(infoId);
		PictureMerge tm = new PictureMerge();
		int n = 10;
		Long frames = videoInfo.getVideoFrames() / n;
		List<BufferedImage> bufferedImageList = new ArrayList<BufferedImage>();
		Long Tar = frames;
		for (int i = 0; i < n - 1; i++) {
			String picPath = req.getSession().getServletContext()
					.getRealPath("")  + "temp" + File.separator
					+ IdUtil.simpleUUID() + ".jpg";
			VideoResolution.getVideoPic(toFile, picPath, Tar);
			Tar = Tar + frames;
			bufferedImageList.add(tm.loadImageLocal(picPath));
		}
		String newPicPath = req.getSession().getServletContext().getRealPath("")
				+ "temp" + File.separator + "thumbnail"
				+ File.separator + IdUtil.simpleUUID() + ".jpg";
		tm.writeImageLocal(newPicPath,
				tm.Merge(PictureMerge.orientation, bufferedImageList));
		// 上传合成图到FTP
		File file = new File(newPicPath);
		String ftpPicPath ="/data/imgs/thumbnail/";
		String ftpPicName = ftpPicPath + file.getName();
		ftp.cd(ftpPicPath);
		boolean b = ftp.upload(ftpPicPath, file.getName(), file);
		if (b) {
			System.out.println("上传缩略图成功");
		}
		try {
			ftp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ftpPicName;
	}
	/**
	 * 将缩略图插入数据库
	 * 
	 * @param picPath
	 * @param infoId
	 */
	public void addthumbnail(String picPath, String infoId) {
		VideoInfo videoInfo = infoMapper.selectById(infoId);
		VideoPic videoPic = new VideoPic();
		Snowflake snowflake = IdUtil.createSnowflake(1, 1);
		long id = snowflake.nextId();
		videoPic.setId(id);
		videoPic.setVideoId(videoInfo.getId());
		videoPic.setPicActualUrl(picPath);
		videoPic.setPicRequestUrl(videoInfo.getId());
		videoPic.setPicType(1);
		vPicMapper.insert(videoPic);

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
				vrDto.setPicActualUrl((String)redisUtil.get(byId.getId()));
				QueryWrapper<VideoUrl> vuqueryWrapper = new QueryWrapper<VideoUrl>();
				vuqueryWrapper.eq("video_id", byId.getId());
				VideoUrl videoUrl = vUrlMapper.selectOne(vuqueryWrapper);
				vrDto.setActualUrl(videoUrl.getRequestUrl());
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
		detail.setActualUrl(videoUrl.getRequestUrl());
		detail.setBulletScreenNum(vData.getBulletScreenNum());
		detail.setLikedNum(vData.getLikedNum());
		detail.setPicUrl(pic);
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