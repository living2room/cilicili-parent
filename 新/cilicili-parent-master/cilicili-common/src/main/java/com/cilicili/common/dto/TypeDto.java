/**
 * 
 */
package com.cilicili.common.dto;

import java.util.List;

import com.cilicili.domain.content.Type;

import lombok.Data;

/**
 * @author 李明睿
 * 2019年5月29日
 */
@Data
public class TypeDto {

	/**
	 * 当前类型
	 */
	private Type type;
	/**
	 * 子类列表
	 */
	private List<TypeDto> typeDtolist;
}



