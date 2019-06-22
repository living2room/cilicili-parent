package com.cilicili.advertisement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cilicili.advertisement.mapper.LeftMapper;
import com.cilicili.domain.advertisement.CenterAdv;
import com.cilicili.domain.advertisement.LeftAdv;
import com.cilicili.domain.advertisement.RightAdv;


@Service
public class LeftService {

	@Autowired
	private LeftMapper leftDao;
	
	public void play() {
		System.out.println("11111119999");
		LeftAdv one = new LeftAdv();
//		buttleDao.addAdvByOne(one);
		leftDao.insert(one);
		
	}
	
	//单条添加
	public void addAdvByOne(LeftAdv left) {
		
		leftDao.insert(left);
	}
	
	//查询所有
	public List<LeftAdv> selAdvAll(){
		
		List<LeftAdv> list = leftDao.selectList(null);
		return list;
	}
	
	//多条件查询
		public List<LeftAdv> selAdvByMoreCon(QueryWrapper<LeftAdv> queryWrapper){
			List<LeftAdv> list = leftDao.selectList(queryWrapper);
			return list;
		}
	
	//查询单条
	public LeftAdv selAdvById(int id) {
		return leftDao.selectById(id);
	}
	
	//删除广告
	public void delAdvById(int id) {
		leftDao.deleteById(id);
	}
	
	//批量删除广告
	public void delMoreAdv(List<Integer> idList) {
		leftDao.deleteBatchIds(idList);
		//buttleDao.delete(ButtleList);
	}
	
	//修改广告
	public void upAdvByOBJ(LeftAdv left) {
		leftDao.updateById(left);
	}
	
	//禁用广告
	public void isUse(List<LeftAdv> ButtleList,int operator,int isNormal) {
		
	}

	public LeftAdv selectmore(int id) {
		return leftDao.selectById(id);
	}
	
	public int selectCount() {
		return leftDao.selectCount(null);
	}

	public IPage<LeftAdv> selAdvByMoreConByPage(Page<LeftAdv> page, QueryWrapper<LeftAdv> queryWrapper) {
		return leftDao.selectPage(page, queryWrapper);
	}
}
