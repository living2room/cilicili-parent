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
@TableName("tb_video_series")
public class VideoSeries {
	private Integer id;
	private String seriesName;
	private String describe;
}
