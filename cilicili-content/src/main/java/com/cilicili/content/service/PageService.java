package com.cilicili.content.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cilicili.bean.content.VideoUrl;
import com.cilicili.content.mapper.VideoInfoMapper;
import com.cilicili.content.mapper.VideoUrlMapper;

/**
 * 页面展示的服务层
 * @author 李明睿
 * 2019年5月17日
 */
@Service
public class PageService {
	@Resource
	private VideoInfoMapper infoMpper;
	@Resource
	private VideoUrlMapper vUrlMapper;
	// TODO 换Map来实现随机插入
	public void getFrontPage() {
		
	}
	/**
	 * @param videoPath
	 * @return
	 */
	public VideoUrl getVideo(String videoPath) {
		QueryWrapper<VideoUrl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("actual_url", videoPath);
		return vUrlMapper.selectOne(queryWrapper );
	}

}
