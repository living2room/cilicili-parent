package com.cilicili.comment.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("tb_delete_reason")
public class DeleteReason {
	
/*
 * id	int	 
delete_reason	varchar	删除原因
costom_reason	varchar	自定义原因

 * */
	@TableId(type=IdType.AUTO)
    private Integer id;

    private String deleteReason;

    private String costomReason;

}