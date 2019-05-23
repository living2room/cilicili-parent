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
@TableName("tb_type")
public class Type {
	private Integer id;
	private String type;
	private Integer typeRating;
	private Integer fatherRatingId;
	private String  typeUrl;
}
