package com.cilicili.content.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cilicili.content.mapper.VideoExamineMapper;
import com.cilicili.domain.content.VideoExamine;

@Service
public class VideoExamineService {
	
	@Resource
	private VideoExamineMapper veMapper;
	
	public void videoVerityY(VideoExamine ve) {
		veMapper.update(ve, null);
	}
	
	public void videoVerityX(VideoExamine ve) {
		veMapper.update(ve, null);
	}
	
	public VideoExamine selectOne(String videoid) {
		QueryWrapper<VideoExamine> queryWrapper = new QueryWrapper<VideoExamine>();
		queryWrapper.eq("video_id", videoid);
		return veMapper.selectOne(queryWrapper);
	}
}
