package com.cilicili.comment.dao;

import com.cilicili.comment.domain.Comment;

public interface CommentDAO {
	int insert(Comment comment);
	int deleteById(int commentId);
	int updateById(Comment comment);
	Comment selectById(int commentId);
}
