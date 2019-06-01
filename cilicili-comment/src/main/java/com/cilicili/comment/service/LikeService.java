package com.cilicili.comment.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.comment.domain.Like;
import com.cilicili.comment.mapper.LikeMapper;

@Service("LikeCountService")
public class LikeService {
	@Resource
	private LikeMapper likeMapper;

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Like> findAll(){
		try {
			return this.likeMapper.selectList(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Like findById(int likeCountId){
		try {
			return this.likeMapper.selectById(likeCountId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void insert(Like like) {
		try {
			this.likeMapper.insert(like);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	
	/*
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int updateById(LikeCount likeCount) {
		try {
			return this.likeCountMapper.updateById(likeCount);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	*/
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int updateById(int likeCountId) {
		try {
			Like like = this.likeMapper.selectById(likeCountId);
			return this.likeMapper.updateById(like);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void deleteById(int likeCountId){
		try {
			this.likeMapper.deleteById(likeCountId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
}
