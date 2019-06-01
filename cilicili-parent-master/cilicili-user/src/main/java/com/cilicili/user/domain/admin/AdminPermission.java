package com.cilicili.user.domain.admin;

import java.util.Date;
import java.util.List;

import com.cilicili.user.domain.user.Role;

/*
 * 权限表的设计
 */
public class AdminPermission {	
	// permission_id varchar权限id
	private Integer permissionId;
	
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
	private List<AdminRole> adminRoleList;

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
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
		this.icon = icon;
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

	public List<AdminRole> getAdminRoleList() {
		return adminRoleList;
	}

	public void setAdminRoleList(List<AdminRole> adminRoleList) {
		this.adminRoleList = adminRoleList;
	}

	public AdminPermission(Integer permissionId, String permissionName, String description, String url,
			String perms, Integer parentId, Integer type, Integer orderNum, String icon, Integer status,
			Date createTime, Date updateTime, List<AdminRole> adminRoleList) {
		super();
		this.permissionId = permissionId;
		this.permissionName = permissionName;
		this.description = description;
		this.url = url;
		this.perms = perms;
		this.parentId = parentId;
		this.type = type;
		this.orderNum = orderNum;
		this.icon = icon;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.adminRoleList = adminRoleList;
	}
  
	public AdminPermission() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AdminPermission" + this.permissionId + "|" + this.permissionName
				+ "|" + this.getDescription() + "|" + this.parentId + "|" + this.getOrderNum()+ this .perms + "|"+ this.url+ this.getStatus();
	}

	
	
}