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
@TableName("tb_video_pic")
public class VideoPic {
	private Long id;
	private String picActualUrl;
	private String picRequestUrl;
	private VideoInfo videoId;
	private String reserved;
}
