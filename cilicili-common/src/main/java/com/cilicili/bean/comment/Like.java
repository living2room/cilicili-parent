package com.cilicili.bean.comment;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("tb_like")
public class Like {
/*
 *like_id	int	点赞数ID
user_id	varchar	点赞人ID
comment_id	int	评论ID
reply_id	int	回复ID
like_status	tinyint	点赞状态：1点赞，0取消赞

 * */
	
	@TableId(type=IdType.AUTO)
	private Integer likeId;

    private String userId;

    private Integer commentId;

    private Integer replyId;
    
    private Integer likeStatus;
    
}