package com.cilicili.user.domain.user;

import java.util.Date;
import java.util.List;

/*
 * 权限表的设计
 */
public class Permission {

	// id int
	private Integer id;
	
	// permission_id varchar权限id
	private String permissionId;
	
	// permission_name varchar 权限名称
	private String permissionName;
	
	// description varchar  权限描述
	private String description;
	
	// url varchar权限访问路径
	private String url;
	
	// perms varchar  权限标识
	private String perms;
	
	// parent_id int  父级权限id
	private Integer parentId;
	
	// type int  类型 0：目录 1：菜单 2：按钮
	private Integer type;
	
	// order_num  排序
	private Integer orderNum;
	
	// icon varchar 图标
	private String icon;
	
	// status int 状态：1有效；2删除
	private Integer status;
	
	// create_time datetime 创建时间
	private Date createTime;
	
	// update_time datetime  更新时间
	private Date updateTime;
	
	//对多个角色
	/*s*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId == null ? null : permissionId.trim();
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName == null ? null : permissionName.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms == null ? null : perms.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon == null ? null : icon.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}