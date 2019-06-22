package com.cilicili.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.cilicili.payment.domain.Discount;

@Mapper
public interface ManageDiscountMapper {
	@Select(value="select * from viptype left join discount on VipID=vipTypeID")
	@Results(value= {
			@Result(column="VipID",property="vipID",jdbcType=JdbcType.INTEGER),
			@Result(column="discountID",property="discountID",jdbcType=JdbcType.INTEGER),
			@Result(column="discountName",property="discountName",jdbcType=JdbcType.VARCHAR),
			@Result(column="discountValue",property="discountValue",jdbcType=JdbcType.INTEGER),
			@Result(column="VipName",property="vipName",jdbcType=JdbcType.VARCHAR)
			
	})
	List<Discount> select();
	
	@Select(value="select * from viptype left join discount on VipID=vipTypeID where vipTypeID=#{vipTypeID,jdbcType=INTEGER}")
	@Results(value= {
			@Result(column="VipID",property="vipID",jdbcType=JdbcType.INTEGER),
			@Result(column="discountID",property="discountID",jdbcType=JdbcType.INTEGER),
			@Result(column="discountName",property="discountName",jdbcType=JdbcType.VARCHAR),
			@Result(column="discountValue",property="discountValue",jdbcType=JdbcType.INTEGER),
			@Result(column="VipName",property="vipName",jdbcType=JdbcType.VARCHAR)
			
	})
	Discount selectByID(Integer vipTypeID);
	
	@Update(value="update discount set discountName=#{discountName,jdbcType=VARCHAR},"
			+ "discountValue=#{discountValue,jdbcType=INTEGER} "
			+ "where vipTypeID=#{vipID,jdbcType=INTEGER}")
	int update(Discount discount);
	
	@Insert(value = "insert into discount (vipTypeID, discountValue, discountName) "
	          + "values(#{vipID,jdbcType=INTEGER}, #{discountValue,jdbcType=INTEGER},#{discountName,jdbcType=VARCHAR})")
	int insert(Discount discount);
	
	@Delete(value="delete from discount where vipTypeID=#{VipID}")
	int deleteByVipTypeID(int VipID);
}
