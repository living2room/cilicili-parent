package com.cilicili.common.dto;
/**
*@author Hym
*
*/

import java.util.List;

import com.cilicili.bean.comment.Comment;
import com.cilicili.bean.comment.Reply;

import lombok.Data;

@Data
public class CommentDto {
	private Comment com;
	private List<Reply> rep;
}
