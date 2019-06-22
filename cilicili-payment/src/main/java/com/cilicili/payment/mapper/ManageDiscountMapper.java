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
	@Select(value="select * from tb_vip_type left join tb_discount on vip_id=vip_type_id")
	@Results(value= {
			@Result(column="vip_id",property="vipID",jdbcType=JdbcType.INTEGER),
			@Result(column="discount_id",property="discountID",jdbcType=JdbcType.INTEGER),
			@Result(column="discount_name",property="discountName",jdbcType=JdbcType.VARCHAR),
			@Result(column="discount_value",property="discountValue",jdbcType=JdbcType.INTEGER),
			@Result(column="vip_name",property="vipName",jdbcType=JdbcType.VARCHAR)
			
	})
	List<Discount> select();
	
	@Select(value="select * from tb_vip_type left join tb_discount on vip_id=vip_type_id where vip_type_id=#{vipTypeID,jdbcType=INTEGER}")
	@Results(value= {
			@Result(column="vip_id",property="vipID",jdbcType=JdbcType.INTEGER),
			@Result(column="discount_id",property="discountID",jdbcType=JdbcType.INTEGER),
			@Result(column="discount_name",property="discountName",jdbcType=JdbcType.VARCHAR),
			@Result(column="discount_value",property="discountValue",jdbcType=JdbcType.INTEGER),
			@Result(column="vip_name",property="vipName",jdbcType=JdbcType.VARCHAR)
			
	})
	Discount selectByID(Integer vipTypeID);
	
	@Update(value="update tb_discount set discount_name=#{discountName,jdbcType=VARCHAR},"
			+ "discount_value=#{discountValue,jdbcType=INTEGER} "
			+ "where vip_type_id=#{vipID,jdbcType=INTEGER}")
	int update(Discount discount);
	
	@Insert(value = "insert into tb_discount (vip_type_id, discount_value, discount_name) "
	          + "values(#{vipID,jdbcType=INTEGER}, #{discountValue,jdbcType=INTEGER},#{discountName,jdbcType=VARCHAR})")
	int insert(Discount discount);
	
	@Delete(value="delete from tb_discount where vip_type_id=#{VipID}")
	int deleteByVipTypeID(int VipID);
}
