package com.cilicili.advertisement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cilicili.advertisement.mapper.RightMapper;
import com.cilicili.domain.advertisement.CenterAdv;
import com.cilicili.domain.advertisement.RightAdv;


@Service
public class RightService {

	@Autowired
	private RightMapper rightDao;
	
	public void play() {
		System.out.println("11111119999");
		RightAdv one = new RightAdv();
//		buttleDao.addAdvByOne(one);
		rightDao.insert(one);
		
	}
	
	//单条添加
	public void addAdvByOne(RightAdv right) {
		
		rightDao.insert(right);
	}
	
	//查询所有
	public List<RightAdv> selAdvAll(){
		
		List<RightAdv> list = rightDao.selectList(null);
		return list;
	}
	
	//多条件查询
		public List<RightAdv> selAdvByMoreCon(QueryWrapper<RightAdv> queryWrapper){
			List<RightAdv> list = rightDao.selectList(queryWrapper);
			return list;
		}
	
	//查询单条
	public RightAdv selAdvById(int id) {
		return rightDao.selectById(id);
	}
	
	//删除广告
	public void delAdvById(int id) {
		rightDao.deleteById(id);
	}
	
	//批量删除广告
	public void delMoreAdv(List<Integer> idList) {
		rightDao.deleteBatchIds(idList);
		//buttleDao.delete(ButtleList);
	}
	
	//修改广告
	public void upAdvByOBJ(RightAdv but) {
		rightDao.updateById(but);
	}
	
	//禁用广告
	public void isUse(List<RightAdv> ButtleList,int operator,int isNormal) {
		
	}

	public RightAdv selectmore(int id) {
		return rightDao.selectById(id);
	}
	
	public int selectCount() {
		return rightDao.selectCount(null);
	}

	public IPage<RightAdv> selAdvByMoreConByPage(Page<RightAdv> page, QueryWrapper<RightAdv> queryWrapper) {
		return rightDao.selectPage(page, queryWrapper);
	}
}
