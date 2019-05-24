/**
 * 
 */
package com.cilicili.bean.content;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author 李明睿
 * 2019年5月23日
 */
@Data
@TableName("tb_video_type")
public class VideoType {
	private Long id;
	private VideoInfo videoId;
	private Type typeId;
	private String reserved;
}
