/**
 * 
 */
package com.cilicili.common.dto;

import java.util.Date;

import com.cilicili.bean.content.Users;

import lombok.Data;

/**
 * @author 李明睿
 * 2019年6月11日
 */
@Data
public class VideoReviewDto {
	private String id;
	private String videoName;
	private Date videoUploadtime;
	private String picActualUrl;
	private String actualUrl;
	private String userId;
	/**
	 * 0未审核；1审核通过；-1审核未通过
	 */
	private Integer videoStatus;
	private Integer resonId;
	private String notPassReason;
	private Date videoLastExamineTime;
	private String videoTitle;
}
