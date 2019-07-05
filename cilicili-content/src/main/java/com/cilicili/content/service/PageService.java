package com.cilicili.content.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cilicili.common.dto.TvAdDto;
import com.cilicili.common.utils.RedisUtil;
import com.cilicili.content.mapper.TypeMapper;
import com.cilicili.content.mapper.VideoDataMapper;
import com.cilicili.content.mapper.VideoInfoMapper;
import com.cilicili.content.mapper.VideoPicMapper;
import com.cilicili.content.mapper.VideoTypeMapper;
import com.cilicili.content.mapper.VideoUrlMapper;
import com.cilicili.domain.content.Type;
import com.cilicili.domain.content.VideoData;
import com.cilicili.domain.content.VideoInfo;
import com.cilicili.domain.content.VideoPic;
import com.cilicili.domain.content.VideoType;
import com.cilicili.domain.content.VideoUrl;

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
	@Resource
	private VideoPicMapper vPicMapper;
	@Resource
	private VideoDataMapper vDataMapper;
	@Resource
	private VideoTypeMapper vtypeMapper;
	@Resource
	private TypeMapper tMapper;
	@Resource
	private RedisUtil redisUtil;
	// TODO 换Map来实现随机插入

	/**根据类型拿到首页视频服务层
	 * @param type 类型
	 * @param index 当前记录
	 * @param size 取的条数
	 * @return
	 */
	public List<TvAdDto> getPageContent(Type type,int index,int size) {
		Page<VideoType> page = new Page<>(index, size);
		List<VideoType> typeList = vtypeMapper.selectVideoTypeByType(page);//拿到该分类下的分页视频
		List<VideoInfo> infoList = new ArrayList<VideoInfo>();
		List<TvAdDto>  tvAdDtos = new ArrayList<TvAdDto>();
		List<VideoPic> picList = new ArrayList<VideoPic>();
		List<VideoData> dataList = new ArrayList<VideoData>();
		//查询出所需信息
		for (int i = 0; i <= typeList.size()-1; i++) {
			QueryWrapper<VideoInfo> queryWrapperVI = new QueryWrapper<>();
			queryWrapperVI.eq("id", typeList.get(i).getVideoId());
			VideoInfo info = infoMpper.selectOne(queryWrapperVI);
			infoList.add(info);
			QueryWrapper<VideoPic> queryWrapperVP = new QueryWrapper<>();
			queryWrapperVP.eq("video_id", info.getId());
			queryWrapperVP.eq("pic_type", 1);
			VideoPic one = vPicMapper.selectOne(queryWrapperVP);
			picList.add(one);
			QueryWrapper<VideoData> queryWrapperVD = new QueryWrapper<>();
			queryWrapperVD.eq("video_id", info.getId());
			VideoData videoData = vDataMapper.selectOne(queryWrapperVD);
			dataList.add(videoData);

		}
		//组装为对象
		for (int i = 0; i <= infoList.size()-1; i++) {
			TvAdDto adDto = new TvAdDto();
			adDto.setId(infoList.get(i).getId());
			adDto.setName(infoList.get(i).getVideoTitle());
			adDto.setVideoDuration(String.valueOf(infoList.get(i).getVideoDuration()));
			adDto.setVideoDescribe(infoList.get(i).getVideoDescribe());
			adDto.setIsVip(infoList.get(i).getVideoIsvip());
			adDto.setPicPath((String)redisUtil.get(infoList.get(i).getId()));
			adDto.setBulletScreenNum(dataList.get(i).getBulletScreenNum());
			adDto.setVideoPlayedNum(dataList.get(i).getVideoPlayed());
			adDto.setLink(dataList.get(i).getVideoId());
			tvAdDtos.add(adDto);
		}
		return tvAdDtos;
	}
	/**
	 * 根据物理地址拿到视频
	 * @param videoPath
	 * @return
	 */
	public VideoUrl getVideo(String videoPath) {
		QueryWrapper<VideoUrl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("actual_url", videoPath);
		return vUrlMapper.selectOne(queryWrapper );
		}
	/**
	 * @return
	 */
	public List<Type> getAllFirstType() {
		QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("type_rating", 1);
		return tMapper.selectList(queryWrapper);
	}
	
	public List<Type> getItsSecondTyps(Type type){
		QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("type_rating", 2);
		queryWrapper.eq("father_rating_id", type.getId());
		return tMapper.selectList(queryWrapper);
	}

}
