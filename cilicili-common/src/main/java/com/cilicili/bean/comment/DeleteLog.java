package com.cilicili.bean.comment;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("tb_delete_log")
public class DeleteLog {
	
/*
* delete_log_id	int	删除日志ID
comment_id	int	删除评论ID
admin_id	int	管理员ID
delete_time	datetime	删除时间
 * */
	@TableId(type=IdType.AUTO)
	private Integer deleteLogId;

    private Integer commentId;

    private Integer adminId;

    private Date deleteTime;

}