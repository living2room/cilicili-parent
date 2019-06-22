package com.cilicili.advertisement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cilicili.advertisement.mapper.CenterMapper;
import com.cilicili.domain.advertisement.CenterAdv;


@Service
public class CenterService {

	@Autowired
	private CenterMapper centerDao;
	
	public void play() {
		System.out.println("11111119999");
		CenterAdv one = new CenterAdv();
//		buttleDao.addAdvByOne(one);
		centerDao.insert(one);
		
	}
	
	//单条添加
	public void addAdvByOne(CenterAdv but) {
		
		centerDao.insert(but);
	}
	
	//查询所有
	public List<CenterAdv> selAdvAll(){//条件查询
		List<CenterAdv> list = centerDao.selectList(null);
		return list;
	}
	//多条件查询
	public List<CenterAdv> selAdvByMoreCon(QueryWrapper<CenterAdv> queryWrapper){
		List<CenterAdv> list = centerDao.selectList(queryWrapper);
		return list;
	}
	//查询单条
	public CenterAdv selAdvById(int id) {
		return centerDao.selectById(id);
	}
	
	//删除广告
	public void delAdvById(int id) {
		centerDao.deleteById(id);
	}
	
	//批量删除广告
	public void delMoreAdv(List<Integer> idList) {
		centerDao.deleteBatchIds(idList);
		//buttleDao.delete(ButtleList);
	}
	
	//修改广告
	public void upAdvByOBJ(CenterAdv but) {
		centerDao.updateById(but);
	}
	
	//禁用广告
	public void isUse(List<CenterAdv> ButtleList,int operator,int isNormal) {
		
	}

	public int selectCount() {
		return centerDao.selectCount(null);
	}

	public CenterAdv selectmore(int id) {
		return centerDao.selectById(id);
	}
	
	public IPage<CenterAdv> selAdvByMoreConByPage(Page<CenterAdv> page, QueryWrapper<CenterAdv> queryWrapper) {
		return centerDao.selectPage(page,queryWrapper);
	}
}
