package com.cilicili.comment.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("tb_like")
public class Like {
/*
 * like_id	int	点赞数ID
user_id	varchar	用户（评论者）ID
comment_id	int	评论ID
like_time	timestamp	点赞时间

 * */
	
	@TableId(type=IdType.AUTO)
	private Integer likeId;

    private String userId;

    private Integer commentId;

    private Date likeTime;
}