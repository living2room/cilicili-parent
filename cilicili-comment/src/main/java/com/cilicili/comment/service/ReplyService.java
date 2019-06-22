package com.cilicili.comment.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.comment.domain.Comment;
import com.cilicili.comment.domain.Reply;
import com.cilicili.comment.mapper.ReplyMapper;

@Service
public class ReplyService {
	@Resource
 	private ReplyMapper replyMapper;
	
	
	/**
	 * 根据评论ID查看该评论下所有回复信息
	 * @param commentId
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Reply findByCommentId(int commentId){
		return replyMapper.selectById(commentId);
	}
	
	/**
	 *  通过replyId查看回复信息
	 * @param replyId
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Reply findById(int replyId){
			return this.replyMapper.selectById(replyId);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int updateById(Reply reply) {
			return this.replyMapper.updateById(reply);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Reply> findAll(){
			return replyMapper.selectList(null);
	}
	
	
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void insert(Reply reply) {
		try {
			this.replyMapper.insert(reply);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void deleteById(int commentReplyId){
		try {
			this.replyMapper.deleteById(commentReplyId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
}
