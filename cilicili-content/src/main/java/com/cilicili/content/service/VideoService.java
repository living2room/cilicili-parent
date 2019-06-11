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

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
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
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.ftp.Ftp;

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

	@Value("${FTP.ADDRESS}")
	private String ftpAddr;
	@Value("${FTP.PORT}")
	private int ftpport;
	@Value("${FTP.USERNAME}")
	private String username;
	@Value("${FTP.PASSWORD}")
	private String password;

	/**
	 * 处理用户上传的视频 WebUploader将会把视频分成几份上传
	 * 
	 * @param req
	 * @param resp
	 */
	public void addvideoProcessor(HttpServletRequest req,
			HttpServletResponse resp, UploadJsonObj jsonObj) {

		String filePath = "/video/";
		MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) req;
		Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
		Ftp ftp = new Ftp(ftpAddr, ftpport, username, password);
		ftp.setMode(cn.hutool.extra.ftp.FtpMode.Active);
		// TODO 前端一次只上传了5M，所以后端解析时会报错
		Snowflake snowflake = IdUtil.createSnowflake(1, 1);

		for (MultipartFile file : files.values()) {
			String localPath = req.getSession().getServletContext()
					.getRealPath("") + "temp\\video\\";
			String localfile = file.getOriginalFilename();
			File localFile = new File(localPath, localfile);
			try {
				localFile.getParentFile().mkdirs();
				file.transferTo(localFile);
			} catch (IllegalStateException | IOException e2) {
				e2.printStackTrace();
			}
			VideoInfo entity = new VideoInfo();
			String fileName = "";
			try {
				fileName = IdUtil.simpleUUID() + "."
						+ VideoResolution.getVideoFormat(localFile);
				// 上传本地文件
				ftp.upload(filePath, localFile);
				Map<String, Object> videoInfo = VideoResolution
						.getVideoInfo(localFile);
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
				addthumbnail(localFile, entity, req, ftp);
				addpreview(localFile, entity, req, ftp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println(fileName);
			File tagetFile = new File(filePath + "video/" + fileName);// 创建文件对象
			if (!tagetFile.exists()) {// 文件名不存在 则新建文件，并将文件复制到新建文件中
				try {
					VideoUrl videoUrl = new VideoUrl();
					long id = snowflake.nextId();
					videoUrl.setId(id);
					videoUrl.setVideoId(entity);
					videoUrl.setActualUrl(filePath);
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
		// 关闭连接
		try {
			ftp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("接收完毕");
	}

	/**
	 * 制作预览图的
	 * 
	 * @param toFile
	 * @param entity
	 */
	public void addpreview(File toFile, VideoInfo entity,
			HttpServletRequest req, Ftp ftp) {
		String localPath = req.getSession().getServletContext().getRealPath("")
				+ "/temp/thumbnail/";
		String pic = IdUtil.simpleUUID() + ".jpg";
		String picPath = "/imgs/preview/" + pic;
		VideoResolution.getVideoPic(toFile, localPath + pic, 10);
		File file = new File(localPath + pic);
		boolean b = ftp.upload(picPath, file.getName(), file);
		if (b) {
			System.out.println("上传预览图成功");
		}
		VideoPic videoPic = new VideoPic();
		Snowflake snowflake = IdUtil.createSnowflake(1, 1);
		long id = snowflake.nextId();
		videoPic.setId(id);
		videoPic.setVideoId(entity);
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

	/**
	 * 视频拆分成帧,制成缩略图并存放数据库
	 * 
	 * @param ftp
	 * 
	 */
	public void addthumbnail(File toFile, VideoInfo videoInfo,
			HttpServletRequest req, Ftp ftp) {
		// TODO 图片服务器
		PictureMerge tm = new PictureMerge();
		int n = 10;
		Long frames = videoInfo.getVideoFrames() / n;
		List<BufferedImage> bufferedImageList = new ArrayList<BufferedImage>();
		for (int i = 0; i < n - 1; i++) {
			String picPath = req.getSession().getServletContext()
					.getRealPath("") + "/temp/" + IdUtil.simpleUUID() + ".jpg";
			VideoResolution.getVideoPic(toFile, picPath, frames);
			frames = frames + frames;
			bufferedImageList.add(tm.loadImageLocal(picPath));
		}
		String newPicPath = req.getSession().getServletContext().getRealPath("")
				+ "/temp/thumbnail/" + IdUtil.simpleUUID() + ".jpg";
		tm.writeImageLocal(newPicPath,
				tm.Merge(PictureMerge.orientation, bufferedImageList));
		// 上传合成图到FTP
		File file = new File(newPicPath);
		boolean b = ftp.upload("/imgs/thumbnail/", file.getName(), file);
		if (b) {
			System.out.println("上传缩略图成功");
		}
		VideoPic videoPic = new VideoPic();
		Snowflake snowflake = IdUtil.createSnowflake(1, 1);
		long id = snowflake.nextId();
		videoPic.setId(id);
		videoPic.setVideoId(videoInfo);
		videoPic.setPicActualUrl(newPicPath);
		videoPic.setPicRequestUrl(videoInfo.getId());
		videoPic.setPicType(1);
		vPicMapper.insert(videoPic);
	}



//	/**分片处理待完成
//	 * @param req
//	 */
//	public void uploadByPieces(MultipartFile file,HttpServletRequest req) {
//
//		int i = 1;
//		String localPath = req.getSession().getServletContext().getRealPath("")
//				+ "temp" + File.separator + "video" + File.separator;
//		String localfile = i + "_" + file.getOriginalFilename();
//		File localFile = new File(localPath, localfile);
//		try {
//			// TODO 查看webloader上传参数制作Dto
//			localFile.getParentFile().mkdirs();
//			file.transferTo(localFile);
//			i++;
//		} catch (IllegalStateException | IOException e2) {
//			e2.printStackTrace();
//		}
//
//	}

}