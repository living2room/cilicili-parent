package com.cilicili.payment.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cilicili.payment.domain.VipType;
import com.cilicili.payment.mapper.VipTypeMapper;

@RunWith(value=SpringRunner.class)
@SpringBootTest
public class MyTest {
	
	@Autowired
	private VipTypeMapper vipTypeMapper;
	
	@Test
	public void vipTypeTest() {
		VipType vipType=new VipType();
		vipType.setVipName("vipName");
		vipType.setVipDescribe("vipDescribe");
		vipType.setVipPrice(10);
	    Integer testout = vipTypeMapper.insert(vipType);
		vipTypeMapper.insert(vipType);
	    System.out.println(testout);
	}
	
}
