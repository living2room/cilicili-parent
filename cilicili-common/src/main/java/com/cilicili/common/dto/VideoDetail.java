/**
 * 
 */
package com.cilicili.common.dto;

import java.util.Date;

import lombok.Data;

/**
 * @author 李明睿
 * 2019年6月27日
 */
@Data
public class VideoDetail {
	private String actualUrl;
	private String picUrl;
	private String videoId;
	private String videoTitle;
	private String videoDescribe;
	private Date videoUploadTime;
	private Long videoPlayed;
	private Long likedNum;
	private Long bulletScreenNum;
}
