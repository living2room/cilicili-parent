/**
 * 
 */
package com.cilicili.content.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cilicili.bean.content.Type;
import com.cilicili.common.dto.TypeTreeJsonObj;
import com.cilicili.content.mapper.TypeMapper;

/**
 * @author 李明睿
 * 2019年5月26日
 */
@Service
public class TypeService{
	@Resource
	private TypeMapper tMapper;
	
	public int addType(Type type) {
		return tMapper.insert(type);
	}
	
	public int editType(Type type) {
		return tMapper.updateById(type);
	}
	
	public int deleteType(int id) {
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

	/**拿到所有类型并封装返回
	 * @return
	 */
	public List<TypeTreeJsonObj> getTypes() {
		List<Type> list = tMapper.selectList(null);
		List<TypeTreeJsonObj> typeTreeList = new ArrayList<TypeTreeJsonObj>();
		for (int i = 0; i < list.size(); i++) {
			TypeTreeJsonObj typeTree = new TypeTreeJsonObj();
			typeTree.setTypeId(list.get(i).getId());
			typeTree.setText(list.get(i).getType());
			if (list.get(i).getFatherRatingId() == 0) {
				typeTree.setParent("#");
				typeTree.setId("jsonTree"+list.get(i).getId());
				typeTree.setIcon("fa fa-folder");
				Map<String, Boolean> state = new HashMap<String, Boolean>();
				state.put("opened", true);
				typeTree.setState(state );
			}else {
				Type byId = tMapper.selectById(list.get(i).getFatherRatingId());
				typeTree.setParent("jsonTree"+byId.getId());
				typeTree.setId("jsonTree"+list.get(i).getId());
				typeTree.setIcon("fa fa-file");
			}
			typeTreeList.add(typeTree);
		}
		return typeTreeList;
	}

	/**根据名字拿到类型对象
	 * @param text
	 * @return
	 */
	public Type getTypeByName(String text) {
		QueryWrapper<Type> queryWrapper = new QueryWrapper<Type>();
		queryWrapper.eq("type", text);
		return tMapper.selectOne(queryWrapper );
	}

	/**
	 * @param id
	 * @return
	 */
	public Type getTypeByid(Integer id) {
		return tMapper.selectById(id);
	}

	/**
	 * @param text
	 * @return
	 */
	public int deleteTypeByName(String text) {
		
		QueryWrapper<Type> wrapper = new QueryWrapper<Type>();
		wrapper.eq("type", text);
		return tMapper.delete(wrapper );
	}

	/**
	 * @return
	 */
	public List<Type> getAllTypes() {
		return tMapper.selectList(null);
	}
	
}
