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
@TableName("tb_video_info")
public class VideoInfo {
	private Integer id;
	private String videoDescribe;
	private String videoFormat;
	private Long videoSize;
	private String videoName;
	private Timestamp videoUploadTime;
	private Integer isVip;
	private VideoPic videoPicId;
	private VideoSeries videoSeriesId;
	private Long videoFrames;
	private Integer videoisAvailable;
	private String reserved;
}
