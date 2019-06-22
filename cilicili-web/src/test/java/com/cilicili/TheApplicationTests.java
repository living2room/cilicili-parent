package com.cilicili;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cilicili.payment.mapper.ManageOrdersMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TheApplicationTests {
//
//	@Test
//	public void contextLoads() {
//	}
//	
//	@Autowired
//	private VipTypeMapper vipTypeMapper;
//
//	@Test
//	public void test() {
//		VipType vipType=new VipType();
//		vipType.setVipID(23);
//		vipType.setVipDescribe("test describe");
//		vipType.setVipName("test update");
//		vipType.setVipPrice(100);
//		vipType.setVipTime(19999);
//		System.out.println(vipTypeMapper.updateDo(vipType));
//	}
	
//	@Test
//	public void test() {
//		System.out.println(vipTypeMapper.selectById("18"));
//	}
	
//	@Test
//	public void test() {
//		System.out.println(vipTypeMapper.delete("20"));
//		
//	}
	
//	@Test
//	public void vipTypeTest() {
//		VipType vipType=new VipType();
//		vipType.setVipName("testVipName");
//		vipType.setVipPrice(10);
//		vipType.setVipDescribe("testVipDescribe");
//		vipType.setVipTime(100000);
//		System.out.println(vipTypeMapper.insert(vipType));
//	}
//	
//	@Test
//	public void vipTypeTest2() {
////		List<VipType> vipTypeList=new List<VipType>;
//		List<VipType> vipTypeList=vipTypeMapper.findAll();
//		PageHelper.startPage(1,5);
//		PageInfo<VipType> pageInfo = new PageInfo<VipType>(vipTypeList);
//		System.out.println(pageInfo.getPages());
//	}
//	
	
//	@Test
//	public void test() {
//		   Date currentTime = new Date();
//		   System.out.println(currentTime);
//		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		   String dateString = formatter.format(currentTime);
//		   System.out.println(dateString);
//		   ParsePosition pos = new ParsePosition(8);
//		   Date currentTime_2 = formatter.parse(dateString, pos);
//		   System.out.println(currentTime_2);
//		   System.out.println("1111");
//	}
//	@Autowired
//	private ManageDiscountMapper manageDiscountMapper;
//	@Test
//	public void test() {
//		System.out.println( manageDiscountMapper.select());
//	}
	
//	@Test
//	public void test() {
////		Discount discount=new Discount();
////		discount.setDiscountName("周年庆2");
////		discount.setDiscountValue(8);
////		discount.setVipID(1);
//		System.out.println(manageDiscountMapper.deleteByVipTypeID(2));
//	}
//	@Autowired
//	private BuyVipMapper buyVipMapper;
//	@Test
//	public void test() {
//		System.out.println(buyVipMapper.select(3));
//	}
//	@Autowired
//	private VipEndTimeMapper vipEndTimeMapper;
//	@Test
//	public void orderTest() {
//		System.out.println(vipEndTimeMapper.update(1561192113000L,1));
//	}
	@Autowired
	private ManageOrdersMapper manageOrdersMapper;
	@Test
	public void test1(){
		System.out.println(manageOrdersMapper.findAll());
	}
}
