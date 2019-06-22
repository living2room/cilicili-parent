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

	
	/**
	 * 通过用户Id查看是否有该用户的点赞记录
	 * @param userId
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Like findByUserId(String userId){
			return likeMapper.selectById(userId);
	}
	
	/**
	 * 通过点赞Id查看点赞记录
	 * @param likeId
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Like findById(int likeId){
			return likeMapper.selectById(likeId);
	}
	

	/**
	 * 根据用户id和文章id信息查询点赞记录
	 * @param like
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Like findLikeDetail(Like like){
		return likeMapper.findLikeDetail(like);
		
	}
	
	
	/**
	 * 添加点赞记录
	 * @param like
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void insert(Like like) {
		 likeMapper.insert(like);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Like> findAll(){
			return likeMapper.selectList(null);
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int updateById(int likeId) {
			Like like = this.likeMapper.selectById(likeId);
			return likeMapper.updateById(like);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public void deleteById(int likeId){
			likeMapper.deleteById(likeId);
	}
}
