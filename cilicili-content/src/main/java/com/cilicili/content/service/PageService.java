package com.cilicili.content.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cilicili.bean.content.VideoInfo;
import com.cilicili.common.dto.TvAdDto;
import com.cilicili.content.mapper.VideoInfoMapper;

/**
 * 页面展示的服务层
 * @author 李明睿
 * 2019年5月17日
 */
@Service
public class PageService {
	@Resource
	private VideoInfoMapper infoMpper;
	// TODO 换Map来实现随机插入
	public void getFrontPage() {
		
	}

}
