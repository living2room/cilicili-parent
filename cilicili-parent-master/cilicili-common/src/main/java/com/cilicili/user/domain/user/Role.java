package com.cilicili.user.domain.user;

import java.util.Date;
import java.util.List;

import com.cilicili.user.domain.admin.AdminPermission;

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
	
	//一个角色有多个权限           
	private List<Permission> permissionList;

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
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public Role(Integer id, String roleId, String roleName, String description, Integer status, Date createTime,
			Date updateTime, List<Permission> permissionList) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.roleName = roleName;
		this.description = description;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.permissionList = permissionList;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	 //stulist和crlist相互引用对方，导致你调用tostring的时候无限递归。去掉两者之一对对方的引用。 
	  @Override public String toString() { return "Role [id=" + id + ", roleId=" +
	  roleId + ", roleName=" + roleName + ", description=" + description +
	  ", status=" + status + ", createTime=" + createTime + ", updateTime=" +
	  updateTime + ", permissionList=" + permissionList + "]"; }
	 
	 

	
	
}