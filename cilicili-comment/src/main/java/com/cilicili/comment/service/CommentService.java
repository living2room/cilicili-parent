package com.cilicili.comment.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.comment.domain.Comment;
import com.cilicili.comment.mapper.CommentMapper;

@Service
public class CommentService {
	
	@Resource
	private CommentMapper commentMapper;
	
	/**
	 * 根据视频ID查看该视频下所有评论信息
	 * @param videoId
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Comment findByVideoId(int videoId){
		return commentMapper.selectById(videoId);
	}
	
	/**
	 * 通过commentId查看评论信息
	 * @param commentId
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Comment findById(int commentId){
		return commentMapper.selectById(commentId);
	}
	
	/**
	 * 添加评论
	 * @param comment
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void insert(Comment comment) {
			this.commentMapper.insert(comment);
	}
	
	/*
	//思路2 分页selectPage
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Comment findByVideoId(int videoId){
		return commentMapper.selectPage(page, queryWrapper);
	}
	
	//3 selectList
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Comment findByVideoId(int videoId){
		return commentMapper.selectList(queryWrapper);
	}
	*/
	
	
	
	
	/**
	 * 查看所有评论
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Comment> findAll(){
			return commentMapper.selectList(null);
	}
	
	
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int updateById(Comment comment) {
			return commentMapper.updateById(comment);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void deleteById(int commentId){
			this.commentMapper.deleteById(commentId);
	}
	

	/**
	 * 更新点赞
	 * @param num
	 * @param commentId
	 * @param userId
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int updateLike(int num,int commentId,int userId) {
			return commentMapper.updateLike(num, commentId, userId);
	}
}
