package com.cilicili.social.service;



import java.util.List;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cilicili.social.mapper.Tb_messageMapper;
import com.cilicili.social.mapper.Tb_u_usersMapper;
import com.cilicilil.domain.social.Message;
import com.cilicilil.domain.social.MessageBox;

@Service
public class MessageService {
	
	@Resource 
	Tb_messageMapper tb_messageMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public int sendMessage (String title,String content,String sent_userid,String recieve_userid) {
		return tb_messageMapper.insert(title, content,sent_userid,recieve_userid);
	}
	
	
	
	@Resource
	Tb_u_usersMapper tb_u_usersMapper;
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public String findByUserName(String user_name) {
		return tb_u_usersMapper.selectByUserName(user_name);		
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Message selectByID(int message_id) {
		return tb_messageMapper.selectByID(message_id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateMessage(int message_id) {
		tb_messageMapper.updateMessage(message_id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public String selectUsernameByUserid(String user_id) {
		return tb_u_usersMapper.selectUsernameByUserid(user_id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<MessageBox> selectReceive(String recieve_userid) {
		return tb_messageMapper.selectReceive(recieve_userid);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<MessageBox> selectSend(String send_userid){
		return tb_messageMapper.selectSend(send_userid);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public int sendBoxDelete(int message_id) {
		return tb_messageMapper.sendBoxDelete(message_id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int reveiceBoxDelete(int message_id) {
		return tb_messageMapper.receiveBoxDelete(message_id);
	}
}
