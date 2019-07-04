package com.cilicili.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.cilicili.domain.payment.RedPackets;

@Mapper
public interface RedPacketsMapper {
	@Select(value="select * from tb_red_packets left join tb_get_red_packets on tb_red_packets.id=red_packets_id")
	@Results(value= {
			@Result(column="id",property="redPacketsID",jdbcType=JdbcType.INTEGER),
			@Result(column="red_packets_name",property="redPacketsName",jdbcType=JdbcType.VARCHAR),
			@Result(column="red_packets_describe",property="redPacketsDescribe",jdbcType=JdbcType.VARCHAR),
			@Result(column="red_packets_price",property="redPacketsPrice",jdbcType=JdbcType.DOUBLE),
			@Result(column="user_id",property="userID",jdbcType=JdbcType.INTEGER)
			
	})
	List<RedPackets> select();
	
	@Select(value="select * from tb_red_packets left join tb_get_red_packets on tb_red_packets.id=red_packets_id where user_id=#{UserID,jdbcType=VARCHAR}")
	@Results(value= {
			@Result(column="id",property="redPacketsID",jdbcType=JdbcType.INTEGER),
			@Result(column="red_packets_name",property="redPacketsName",jdbcType=JdbcType.VARCHAR),
			@Result(column="red_packets_describe",property="redPacketsDescribe",jdbcType=JdbcType.VARCHAR),
			@Result(column="red_packets_price",property="redPacketsPrice",jdbcType=JdbcType.DOUBLE),
			@Result(column="user_id",property="userID",jdbcType=JdbcType.INTEGER)
			
	})
	List<RedPackets> selectByUserID(String UserID);
	
	@Delete(value="delete from tb_red_packets where id=#{id,jdbcType=INTEGER}")
	int delete(Integer id);
	
	@Insert(value="insert into tb_red_packets(red_packets_name,red_packets_describe,red_packets_price)values(#{redPacketsName,jdbcType=VARCHAR},#{redPacketsDescribe,jdbcType=VARCHAR},#{redPacketsValue,jdbcType=DOUBLE})")
	int insert(String redPacketsName,String redPacketsDescribe,double redPacketsValue);
	
}
