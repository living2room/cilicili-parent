package com.cilicili.comment.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cilicili.comment.dao.CommentDAO;
import com.cilicili.comment.domain.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insert(Comment comment) {
		String sql = "insert into tb_comment(content) values(?)";
		return jdbcTemplate.update(sql, comment.getContent());
	}

	@Override
	public int deleteById(int commentId) {
		String sql = "delete from tb_comment where comment_id=?";
		return jdbcTemplate.update(sql, commentId);
	}

	@Override
	public int updateById(Comment comment) {
		String sql = "update tb_comment set content=? where comment_id=?";
		return jdbcTemplate.update(sql, comment.getContent(), comment.getCommentId());
	}

	@Override
	public Comment selectById(int commentId) {
		String sql = "select * from tb_comment where comment_id=?";
		return jdbcTemplate.queryForObject(sql, new RowMapper<Comment>() {
			@Override
			public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Comment comment = new Comment();
				comment.setCommentId(rs.getInt("comment_id"));
				comment.setContent(rs.getString("content"));
				return comment;
			}
		}, commentId);
	}

}
