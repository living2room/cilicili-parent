package com.cilicili.comment.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cilicili.comment.domain.Comment;

@Mapper
public interface CommentMapper extends BaseMapper<Comment>{
	int updateLike(int num,int commentId,int userId);
}