package com.cilicili.comment.mapper;

import com.cilicili.comment.domain.CommentReply;

public interface CommentReplyMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(CommentReply record);

    int insertSelective(CommentReply record);

    CommentReply selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(CommentReply record);

    int updateByPrimaryKeyWithBLOBs(CommentReply record);

    int updateByPrimaryKey(CommentReply record);
}