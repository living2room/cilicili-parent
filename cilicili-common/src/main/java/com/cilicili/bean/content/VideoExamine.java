/**
 * 
 */
package com.cilicili.bean.content;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author 李明睿
 * 2019年5月23日
 */
@Data
@TableName("tb_video_examine")
public class VideoExamine {
	private Integer id;
	private VideoInfo videoId;
	private Integer videoStatus;
	private Timestamp videoLastExamineTime;
	private Timestamp videoApprovalTime;
	private  AdminUser adminId;
	private String reserved;
}
