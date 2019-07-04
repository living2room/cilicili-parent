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

import com.cilicili.domain.payment.VipType;

@Mapper
public interface VipTypeMapper {
	
	@Insert(value = "insert into tb_vip_type (vip_price, vip_name, vip_describe,vip_time) "
		          + "values(#{VipPrice, jdbcType=DOUBLE}, #{VipName, jdbcType=VARCHAR}, #{VipDescribe, jdbcType=VARCHAR}, #{VipTime ,jdbcType=DOUBLE})")
	int insert(VipType vipType);
	
	@Select(value="select * from tb_vip_type")
	@Results(value= {
			@Result(column="vip_id",property="VipID",jdbcType=JdbcType.INTEGER),
			@Result(column="vip_price",property="VipPrice",jdbcType=JdbcType.DECIMAL),
			@Result(column="vip_name",property="VipName",jdbcType=JdbcType.VARCHAR),
			@Result(column="vip_describe",property="VipDescribe",jdbcType=JdbcType.VARCHAR),
			@Result(column="vip_time",property="VipTime",jdbcType=JdbcType.DOUBLE)
	})
	List<VipType> findAll();
	
	@Delete(value="DELETE FROM tb_vip_type where vip_id="
				  + "#{VipID, jdbcType=INTEGER}")
	int delete(String VipID);
	
	@Select(value="select * from tb_vip_type where vip_id="
				 +"#{VipID,jdbcType=INTEGER}")
	@Results(value= {
			@Result(column="vip_id",property="VipID",jdbcType=JdbcType.INTEGER),
			@Result(column="vip_price",property="VipPrice",jdbcType=JdbcType.DECIMAL),
			@Result(column="vip_name",property="VipName",jdbcType=JdbcType.VARCHAR),
			@Result(column="vip_describe",property="VipDescribe",jdbcType=JdbcType.VARCHAR),
			@Result(column="vip_time",property="VipTime",jdbcType=JdbcType.DOUBLE)
	})
	VipType selectById(String VipId);
	
	@Update(value="update tb_vip_type set vip_price=#{VipPrice,jdbcType=DECIMAL},"
				+ "vip_name=#{VipName,jdbcType=VARCHAR},"
				+ "vip_describe=#{VipDescribe,jdbcType=VARCHAR},"
				+ "vip_time=#{VipTime,jdbcType=DOUBLE}"
				+ "where vip_id=#{VipID,jdbcType=INTEGER}")
	int updateDo(VipType vipType);
}
