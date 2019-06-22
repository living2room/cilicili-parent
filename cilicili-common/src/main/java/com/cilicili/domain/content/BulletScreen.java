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
@TableName("tb_bullet_screen")
public class BulletScreen {
	private Integer id;
	private String bulletScreenContent;
	private String bulletScrennColor;
	private String bulletScreenPosition;
	private String bulletScreenTime;
	private String bulletScreenIsNew;
}
