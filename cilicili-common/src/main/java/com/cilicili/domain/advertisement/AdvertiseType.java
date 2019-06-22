package com.cilicili.domain.advertisement;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("tb_advertise_type")
public class AdvertiseType {

//	id	int
	private int id;
//	type_name	varchar
	private String typeName;
//	controller_path	varchar
	private String controllerPath;

}
