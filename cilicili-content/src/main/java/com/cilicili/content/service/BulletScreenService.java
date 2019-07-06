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
import com.cilicili.content.mapper.BulletScreenMapper;
import com.cilicili.content.mapper.VideoBulletScreenMapper;
import com.cilicili.content.mapper.VideoDataMapper;
import com.cilicili.domain.content.BulletScreen;
import com.cilicili.domain.content.VideoBulletScreen;
import com.cilicili.domain.content.VideoData;
import com.cilicili.domain.user.user.Users;

import cn.hutool.core.util.IdUtil;

/**
 * @author 李明睿
 * 2019年7月6日
 */
@Service
public class BulletScreenService {
	
	@Resource
	private VideoBulletScreenMapper vBsMapper;
	@Resource
	private BulletScreenMapper bsMapper;
	@Resource
	private VideoDataMapper vDataMapper;
	
	/**
	 * @param vi
	 */
	public List<BulletScreen> getBulletScreen(String vi) {
		QueryWrapper<VideoBulletScreen> vbsqueryWrapper = new QueryWrapper<VideoBulletScreen>();
		vbsqueryWrapper.eq("video_id", vi);
		List<VideoBulletScreen> vbsList = vBsMapper.selectList(vbsqueryWrapper);
		List<BulletScreen>bulletScreenList = new ArrayList<BulletScreen>();
		
		for (VideoBulletScreen videoBulletScreen : vbsList) {
			BulletScreen bulletScreen = bsMapper.selectById(videoBulletScreen.getBulletScreenId());
			bulletScreenList.add(bulletScreen);
		}
		return bulletScreenList;
	}

	/**
	 * @param user
	 * @param vid
	 * @param time
	 * @param text
	 * @param color
	 * @param type
	 */
	public void insertBs(Users user, String vid, String time, String text,
			String color, Integer type) {
		BulletScreen bs = new BulletScreen();
		bs.setFont(12);
		bs.setType(type);
		bs.setTime(time);
		bs.setUser(user.getUserId());
		bs.setColor(color);
		bs.setText(text);
		String id= IdUtil.fastSimpleUUID();
		bs.setId(id);
		bsMapper.insert(bs);
		VideoBulletScreen entity = new VideoBulletScreen();
		entity.setUserId(user.getUserId());
		entity.setVideoId(vid);
		entity.setBulletScreenId(id);
		vBsMapper.insert(entity );
		
		QueryWrapper<VideoData> queryWrapper = new QueryWrapper<VideoData>();
		queryWrapper.eq("video_id", vid);
		VideoData videoData = vDataMapper.selectOne(queryWrapper );
		VideoData vData = new VideoData();
		vData.setBulletScreenNum(videoData.getBulletScreenNum()+1);
		QueryWrapper<VideoData> updateWrapper = new QueryWrapper<VideoData>();
		updateWrapper.eq("video_id", vid);
		vDataMapper.update(vData, updateWrapper);
	}
}
