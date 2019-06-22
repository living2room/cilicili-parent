package com.cilicili.advertisement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cilicili.advertisement.domain.CenterAdv;
import com.cilicili.advertisement.domain.ReserveAdv;
import com.cilicili.advertisement.mapper.ReserveMapper;


@Service
public class ReserveService {

	@Autowired
	private ReserveMapper reserveDao;
	
	public void play() {
		System.out.println("11111119999");
		ReserveAdv one = new ReserveAdv();
//		buttleDao.addAdvByOne(one);
		reserveDao.insert(one);
		
	}
	
	//单条添加
	public void addAdvByOne(ReserveAdv right) {
		
		reserveDao.insert(right);
	}
	
	//查询所有
	public List<ReserveAdv> selAdvAll(){
		
		List<ReserveAdv> list = reserveDao.selectList(null);
		return list;
	}
	
	//多条件查询
		public List<ReserveAdv> selAdvByMoreCon(QueryWrapper<ReserveAdv> queryWrapper){
			List<ReserveAdv> list = reserveDao.selectList(queryWrapper);
			return list;
		}
	
	//查询单条
	public ReserveAdv selAdvById(int id) {
		return reserveDao.selectById(id);
	}
	
	//删除广告
	public void delAdvById(int id) {
		reserveDao.deleteById(id);
	}
	
	//批量删除广告
	public void delMoreAdv(List<Integer> idList) {
		reserveDao.deleteBatchIds(idList);
		//buttleDao.delete(ButtleList);
	}
	
	//修改广告
	public void upAdvByOBJ(ReserveAdv but) {
		reserveDao.updateById(but);
	}
	
	//禁用广告
	public void isUse(List<ReserveAdv> ButtleList,int operator,int isNormal) {
		
	}

	public ReserveAdv selectmore(int id) {
		return reserveDao.selectById(id);
	}
	
	public int selectCount() {
		return reserveDao.selectCount(null);
	}

	public IPage<ReserveAdv> selAdvByMoreConByPage(Page<ReserveAdv> page, QueryWrapper<ReserveAdv> queryWrapper) {
		return reserveDao.selectPage(page, queryWrapper);
	}
}
