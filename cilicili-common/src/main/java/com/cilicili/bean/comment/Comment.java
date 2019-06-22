package com.cilicili.bean.comment;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("tb_comment")
public class Comment {
/*
* comment_id	int	评论ID
video_id	int	视频ID
user_id	varchar	评论人ID
comment_content	text	评论内容
comment_status	tinyint	评论内容状态：默认正常1，已删除为0
comment_like_num	int	评论的点赞数
comment_time	timestamp	评论时间
* */
	
	@TableId(type=IdType.AUTO)
	private Integer commentId;

    private String videoId;

    private String userId;

    private Integer commentStatus;

    private Integer commentLikeNum;

    private Date commentTime;

    private String commentContent;
}