/**
 * 
 */
package com.cilicili.domain.content;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author 李明睿
 * 2019年5月23日
 */
@Data
@TableName("tb_bullet_screen")
public class BulletScreen {
	private Integer id;
	private String bulletScreenContent;
	private String bulletScrennColor;
	private Integer bulletScreenPosition;
	private String bulletScreenTime;
	private String bulletScreeFnont;
	private Timestamp bulletScreenTimestamp;
	private Integer bulletScreenPool;
	private String bulletScreenUser;
}
