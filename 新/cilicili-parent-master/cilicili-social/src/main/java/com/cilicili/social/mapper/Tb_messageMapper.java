package com.cilicili.social.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cilicilil.domain.social.Message;
import com.cilicilil.domain.social.MessageBox;

@Mapper
public interface Tb_messageMapper {
	@Insert(value="insert into tb_message(message_title,message_content,sent_userid,recieve_userid)values(#{message_title,jdbcType=LONGVARCHAR},#{message_content,jdbcType=LONGVARCHAR},#{sent_userid,jdbcType=VARCHAR},#{recieve_userid,jdbcType=VARCHAR})")
	int insert(String message_title,String message_content,String sent_userid,String recieve_userid);
	
	@Select(value="select * from tb_message where message_id= #{message_id,jdbcType=INTEGER}")
	Message selectByID(int message_id);
	
	@Update(value="update tb_message set delete_by_reveice=-1 where message_id=#{message_id,jdbcType=INTEGER}")
	int updateMessage(int message_id);
	
	@Select(value="select * from tb_message,tb_u_users where recieve_userid=#{recieve_userid,jdbcType=VARCHAR} and user_id=recieve_userid and delete_by_reveice>-3 order by message_id desc")
	List<MessageBox> selectReceive(String recieve_userid);
	
	@Select(value="select * from tb_message,tb_u_users where sent_userid=#{send_userid,jdbcType=VARCHAR} and user_id=sent_userid and delete_by_sent>-3 order by message_id desc")
	List<MessageBox> selectSend(String send_userid);
	
	@Update(value="update tb_message set delete_by_sent =-3 where message_id=#{message_id,jdbcType=INTEGER}") 
	int sendBoxDelete(int message_id);
	
	@Update(value="update tb_message set delete_by_reveice=-3 where message_id=#{message_id,jdbcType=INTEGER}")
	int receiveBoxDelete(int message_id);
}
