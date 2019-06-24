package com.cilicili.domain.content;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author 李明睿
 * 2019年5月23日
 */
@Data
@TableName("tb_video_user")
public class VideoUser {
	private String userId;
	private String videoId;
	private Integer isVip;
	private String reserved;
}
