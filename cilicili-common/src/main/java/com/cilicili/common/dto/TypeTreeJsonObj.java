/**
 * 
 */
package com.cilicili.common.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**jsTree返回格式封装
 * @author 李明睿
 * 2019年6月9日
 */
@Data
public class TypeTreeJsonObj {
	/**
	 * 对应的‘LI’ 节点上设置html标签的ID属性. 请确保ID的唯一性，每个节点的ID都应该不一样
	 */
	private String id;
	/**
	 * 节点名
	 */
	private String text;
	/**
	 * 父节点的ID
	 */
	private String parent;
	/**
	 * 
	 * state - 对象类型，一个节点的状态有一下几种:
		    selected - 节点处于被选中状态
		    opened - 节点处于打开状态
		    disabled - 节点不可选
		    checked - 用于checkbox插件 - 勾选该checkbox(只有当 tie_selection 处于 false时有效)
		    undetermined - 用于checkbox插件 - 状态待定 (只有启用懒加载并且节点没有被加载时生效).
	 * 
	 */
	private Map<String,Boolean> state;
	/**
	 * 用于type插件
	 */
	private String type;
	/**
	 * 节点图标
	 */
	private String icon;
	
	/**
	 * Type表中的ID
	 */
	private Integer typeId;
	/**
	 * 包含DOM属性的对象， 会追加到该节点对应的LI标签.
	 */
	private String li_attr;
	/**
	 * 包含DOM属性的对象， 会追加到该节点对应的A标签.
	 */
	private String a_attr;
	
}
