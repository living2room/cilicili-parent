package com.cilicili.comment.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("tb_reply")
public class Reply {
	
/*
 * reply_id	int	对评论的回复ID
comment_id	int	父评论ID
user_id	varchar	用户（评论者）ID
reply_content	text	回复内容
reply_status	tinyint	回复内容状态：默认正常1，已删除为0
reply_like_num	int	回复点赞数
reply_time	timestamp	回复时间
 * */
	
	@TableId(type=IdType.AUTO)
	private Integer replyId;
	
	private Integer commentId;

    private String userId;

    private Integer replyStatus;

    private Integer replyLikeNum;

    private Date replyTime;

    private String replyContent;
}