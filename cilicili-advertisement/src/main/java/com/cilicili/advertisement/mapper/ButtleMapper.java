package com.cilicili.advertisement.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cilicili.advertisement.domin.Buttle_Adv;




@Mapper
public interface ButtleMapper extends BaseMapper<Buttle_Adv> {

//	public void addAdv(List<Buttle_Adv> ButtleList);//批量添加
//	public void addAdvByOne(Buttle_Adv one);//单条添加
//	public void delAdvById(int id);//单条删除
//	public void upAdvByOBJ(Buttle_Adv one);//修改
//	public void isUse(@Param("ButtleList")List<Buttle_Adv> ButtleList,@Param("operator")int operator,@Param("isNormal")int isNormal);//禁用广告还是使用广告
//	public List<Buttle_Adv> selAdvAll();//查询所有
//	public Buttle_Adv selAdvById(int id);//查询一个
}
