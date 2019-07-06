/**
 * 
 */
package com.cilicili.domain.content;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cilicili.domain.user.user.Users;

import lombok.Data;

/**
 * @author 李明睿
 * 2019年5月23日
 */
@Data
@TableName("tb_video_bullet_screen")
public class VideoBulletScreen {
	private String userId;
	private String videoId;
	private String bulletScreenId;
	private String reserved;
}
