/**
 * 
 */
package com.cilicili.content.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cilicili.bean.content.Type;
import com.cilicili.content.mapper.TypeMapper;

/**
 * @author 李明睿
 * 2019年5月26日
 */
@Service
public class TypeService{
	@Resource
	private TypeMapper tMapper;
	
	int addType(Type type) {
		return tMapper.insert(type);
	}
	
	int editType(Type type) {
		return tMapper.updateById(type);
	}
	
	int deleteType(int id) {
		return tMapper.deleteById(id);
	}
	
	/** 先拿到同一类型下的所有类型并封装,
	 * 再拿到所有类型
	 * @return
	 */
	List<Type> getFirstTypes(){
		QueryWrapper<Type> queryWrapper = new QueryWrapper<Type>();
		queryWrapper.eq("type_rating", 1);
		List<Type> firstlist = tMapper.selectList(queryWrapper);
		for (int i = 0; i < firstlist.size(); i++) {
			queryWrapper = new QueryWrapper<Type>();
			queryWrapper.eq("father_rating_id", firstlist.get(i).getId());
			List<Type> secondlist = tMapper.selectList(queryWrapper );
			
		}
		//TODO 怎么封装返回？
		return firstlist;
	}
	
}
