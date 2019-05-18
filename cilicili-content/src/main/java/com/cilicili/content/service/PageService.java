package com.cilicili.content.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cilicili.bean.content.Type2;
import com.cilicili.content.mapper.TypeMapper;

/**
 * @author 李明睿
 * 2019年5月17日
 */
@Service
public class PageService {
	@Resource
	TypeMapper tMapper;
	
	public int type(Integer id){
		
		Type2 entity = new Type2();
		entity.setId(id);
		int i = tMapper.insert(entity);
		
		return i;
	}
}
