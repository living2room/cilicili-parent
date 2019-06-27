/**
 * 
 */
package com.cilicili.domain.content;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author 李明睿
 * 2019年5月23日
 */
@Data
@TableName("tb_video_data")
public class VideoData {
	private	String videoId;
	private Long videoPlayed;
	private Long collectionNum;
	private Long commonNum;
	private Long likedNum;
	private Long bulletScreenNum;
}
