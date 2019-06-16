/**
 * 
 */
package com.cilicili.bean.content;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author 李明睿
 * 2019年6月12日
 */
@Data
@TableName("tb_not_pass_reason")
public class NotPassReason {
	private Integer id;
	private String notPassReason;
	private String  reserved;
}
