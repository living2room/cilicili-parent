package com.cilicili.advertisement.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cilicili.advertisement.domain.AdvertiseType;
import com.cilicili.advertisement.domain.ListSetClass;
import com.cilicili.advertisement.mapper.AdvertiseTypeMapper;


@Service
public class AdvertiseTypeService {

	@Autowired
	private AdvertiseTypeMapper advertiseDao;
	
	public void play() {
		System.out.println("11111119999");
		AdvertiseType one = new AdvertiseType();
//		buttleDao.addAdvByOne(one);
		advertiseDao.insert(one);
		
	}
	
	//单条添加
	public void addAdvByOne(AdvertiseType right) {
		
		advertiseDao.insert(right);
	}
	
	//查询所有
	public List<AdvertiseType> selAdvAll(HttpServletRequest request){
		List<AdvertiseType> list = new ArrayList<AdvertiseType>();
		ListSetClass typelist = new ListSetClass();//一个list集合类
		typelist.setTypelist(list);
		list = advertiseDao.selectList(null);
		//for (AdvertiseType advertise : list) { System.out.println(advertise); }
		//typelist.setTypelist(list);
		return list;
	}
	
	
}
