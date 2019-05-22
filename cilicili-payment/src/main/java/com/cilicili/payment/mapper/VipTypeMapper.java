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

import com.cilicili.payment.domain.VipType;

@Mapper
public interface VipTypeMapper {
	
	@Insert(value = "insert into viptype (VipPrice, VipName, VipDescribe,VipTime) "
		          + "values(#{VipPrice, jdbcType=DOUBLE}, #{VipName, jdbcType=VARCHAR}, #{VipDescribe, jdbcType=VARCHAR}, #{VipTime ,jdbcType=DOUBLE})")
	int insert(VipType vipType);
	
	@Select(value="select * from viptype")
	@Results(value= {
			@Result(column="VipID",property="VipID",jdbcType=JdbcType.INTEGER),
			@Result(column="VipPrice",property="VipPrice",jdbcType=JdbcType.DECIMAL),
			@Result(column="VipName",property="VipName",jdbcType=JdbcType.VARCHAR),
			@Result(column="VipDescribe",property="VipDescribe",jdbcType=JdbcType.VARCHAR),
			@Result(column="VipTime",property="VipTime",jdbcType=JdbcType.DOUBLE)
	})
	List<VipType> findAll();
	
	@Delete(value="DELETE FROM viptype where VipID="
				  + "#{VipID, jdbcType=INTEGER}")
	int delete(String VipID);
	
	@Select(value="select * from viptype where VipID="
				 +"#{VipID,jdbcType=INTEGER}")
	@Results(value= {
			@Result(column="VipID",property="VipID",jdbcType=JdbcType.INTEGER),
			@Result(column="VipPrice",property="VipPrice",jdbcType=JdbcType.DECIMAL),
			@Result(column="VipName",property="VipName",jdbcType=JdbcType.VARCHAR),
			@Result(column="VipDescribe",property="VipDescribe",jdbcType=JdbcType.VARCHAR),
			@Result(column="VipTime",property="VipTime",jdbcType=JdbcType.DOUBLE)
	})
	VipType selectById(String VipId);
	
	@Update(value="update viptype set VipPrice=#{VipPrice,jdbcType=DECIMAL},"
				+ "VipName=#{VipName,jdbcType=VARCHAR},"
				+ "VipDescribe=#{VipDescribe,jdbcType=VARCHAR},"
				+ "VipTime=#{VipTime,jdbcType=DOUBLE}"
				+ "where VipID=#{VipID,jdbcType=INTEGER}")
	int updateDo(VipType vipType);
}
