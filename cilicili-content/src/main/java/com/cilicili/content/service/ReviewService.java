/**
 * 
 */
package com.cilicili.content.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cilicili.common.dto.VideoReviewDto;
import com.cilicili.common.utils.RedisUtil;
import com.cilicili.content.mapper.VideoInfoMapper;
import com.cilicili.content.mapper.VideoPicMapper;
import com.cilicili.content.mapper.VideoUrlMapper;
import com.cilicili.content.mapper.VideoUserMapper;
import com.cilicili.domain.content.VideoInfo;
import com.cilicili.domain.content.VideoPic;
import com.cilicili.domain.content.VideoUrl;
import com.cilicili.domain.content.VideoUser;

/**视频审查的业务层
 * @author 李明睿
 * 2019年6月12日
 */
@Service
public class ReviewService {

	@Resource
	private VideoInfoMapper infoMapper;
	@Resource
	private VideoUserMapper vUserMapper;
	@Resource
	private VideoUrlMapper vUrlMapper;
	@Resource
	private VideoPicMapper vPicMapper;
	
	@Resource
	private RedisUtil redisUtil;
	
	/**获得视频审查信息
	 * @param text
	 */
	public VideoReviewDto getReviewInfo(String text) {
		QueryWrapper<VideoInfo> vqueryWrapper = new QueryWrapper<VideoInfo>();
		vqueryWrapper.eq("id", text);
		//List<VideoReviewDto> vrDtoList = new ArrayList<VideoReviewDto>();
		VideoInfo byId = infoMapper.selectOne(vqueryWrapper);
		//for (int i = 0; i < vrDtoList.size(); i++) {
			VideoReviewDto vrDto = new VideoReviewDto();
			vrDto.setId(text);
			vrDto.setVideoTitle(byId.getVideoTitle());
			QueryWrapper<VideoUser> uqueryWrapper = new QueryWrapper<VideoUser>();
			uqueryWrapper.eq("video_id", text);
			VideoUser videoUser = vUserMapper.selectOne(uqueryWrapper);
			vrDto.setUserId(videoUser.getUserId());
			QueryWrapper<VideoUrl> vUqueryWrapper = new QueryWrapper<VideoUrl>();
			vUqueryWrapper.eq("video_id", text);
			VideoUrl videoUrl = vUrlMapper.selectOne(vUqueryWrapper);
			vrDto.setActualUrl(videoUrl.getRequestUrl());
			QueryWrapper<VideoPic> picqueryWrapper = new QueryWrapper<VideoPic>();
			picqueryWrapper.eq("video_id", text);
			picqueryWrapper.eq("pic_type", 2);
			vrDto.setPicActualUrl(redisUtil.get(text).toString());
			//vrDtoList.add(vrDto);
		//}
		return vrDto;
	}

}
