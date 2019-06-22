package com.cilicili.user.domain.user;

import java.util.Date;
import java.util.List;

public class Role {

	// id int
	private Integer id;

	// role_id varchar 角色id
	private String roleId;

	// role_name varchar 角色名称
	private String roleName;

	// description varchar 角色描述
	private String description;

	// status int  状态：1有效；2删除
	private Integer status;

	// create_time datetime 创建时间
	private Date createTime;

	// update_time datetime 更新时间
	private Date updateTime;
	
	//一个用户有多个角色
	/* 11private List<User> userList; */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId == null ? null : roleId.trim();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
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