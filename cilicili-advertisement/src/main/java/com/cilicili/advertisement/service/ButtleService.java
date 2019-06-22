package com.cilicili.advertisement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cilicili.advertisement.domin.Buttle_Adv;
import com.cilicili.advertisement.mapper.ButtleMapper;


@Service
public class ButtleService {

	@Autowired
	private ButtleMapper buttleDao;
	
	public void play() {
		System.out.println("11111119999");
		Buttle_Adv one = new Buttle_Adv();
//		buttleDao.addAdvByOne(one);
		buttleDao.insert(one);
		
	}
	
	//单条添加
	public void addAdvByOne(Buttle_Adv but) {
		
		buttleDao.insert(but);
	}
	
	//查询所有
	public List<Buttle_Adv> selAdvAll(){
		
		List<Buttle_Adv> list = buttleDao.selectList(null);
		return list;
	}
	
	//查询单条
	public Buttle_Adv selAdvById(int id) {
		return buttleDao.selectById(id);
	}
	
	//删除广告
	public void delAdvById(int id) {
		buttleDao.deleteById(id);
	}
	
	//批量删除广告
	public void delMoreAdv(List<Integer> idList) {
		buttleDao.deleteBatchIds(idList);
		//buttleDao.delete(ButtleList);
	}
	
	//修改广告
	public void upAdvByOBJ(Buttle_Adv but) {
		buttleDao.updateById(but);
	}
	
	//禁用广告
	public void isUse(List<Buttle_Adv> ButtleList,int operator,int isNormal) {
		
	}
}
