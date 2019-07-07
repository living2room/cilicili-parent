/**
 * 
 */
package com.cilicili.content.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cilicili.content.mapper.BulletScreenMapper;
import com.cilicili.content.mapper.VideoBulletScreenMapper;
import com.cilicili.domain.content.BulletScreen;
import com.cilicili.domain.content.VideoBulletScreen;

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
}
