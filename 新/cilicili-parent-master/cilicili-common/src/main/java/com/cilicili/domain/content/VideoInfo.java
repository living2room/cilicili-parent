/**
 * 
 */
package com.cilicili.domain.content;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author 李明睿
 * 2019年5月23日
 */
@Data
@TableName("tb_video_info")
public class VideoInfo {
	private String id;
	private String videoTitle;
	private String videoDescribe;
	private Long videoDuration;
	private String videoFormat;
	private String videoSize;
	private String videoName;
	private Date videoUploadTime;
	private Integer videoIsvip;
	private VideoPic videoPicId;
	private VideoSeries videoSeriesId;
	private Long videoFrames;
	private Integer videoIsAvailable;
	private String reserved;
}
