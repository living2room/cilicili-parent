package com.cilicili.comment.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cilicili.comment.domain.Like;

@Mapper
public interface LikeMapper extends BaseMapper<Like>{
	Like findLikeDetail(Like like);
}