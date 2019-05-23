package com.cilicili.bean.content;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author 李明睿
 * 2019年5月23日
 */
@Data
@TableName("tb_video_user")
public class VideoUser {
	private Integer id;
	private Users userId;
	private VideoInfo videoId;
	private Integer isVip;
	private String reserved;
}
