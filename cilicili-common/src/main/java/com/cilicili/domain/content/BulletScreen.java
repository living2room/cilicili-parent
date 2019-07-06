/**
 * 
 */
package com.cilicili.domain.content;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author 李明睿
 * 2019年5月23日
 */
@Data
@TableName("tb_bullet_screen")
public class BulletScreen {
	private String id;
	private String text;
	private String color;
	private Integer type;
	private String time;
	private Integer font;
	private Timestamp timestamp;
	private Integer pool;
	private String user;
}
