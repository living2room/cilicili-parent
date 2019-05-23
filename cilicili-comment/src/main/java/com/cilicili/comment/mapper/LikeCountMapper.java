package com.cilicili.comment.mapper;

import com.cilicili.comment.domain.LikeCount;

public interface LikeCountMapper {
    int deleteByPrimaryKey(Integer likeCountId);

    int insert(LikeCount record);

    int insertSelective(LikeCount record);

    LikeCount selectByPrimaryKey(Integer likeCountId);

    int updateByPrimaryKeySelective(LikeCount record);

    int updateByPrimaryKey(LikeCount record);
}