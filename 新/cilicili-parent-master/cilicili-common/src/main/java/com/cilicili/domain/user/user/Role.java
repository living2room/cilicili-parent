package com.cilicili.domain.user.user;

import java.util.Date;
import java.util.List;

import com.cilicili.domain.user.admin.AdminPermission;

public class Role {

	// id int
	private Integer id;

	// role_id varchar 角色id
	private String roleId;

	// role_name varchar 角色名称
	private String roleName;

	// description varchar 角色描述
	private String description;

	// status int 状态：1有效；2删除
	private Integer status;

	// 一个角色有多个权限
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

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public Role(Integer id, String roleId, String roleName, String description, Integer status,
			List<Permission> permissionList) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.roleName = roleName;
		this.description = description;
		this.status = status;
		this.permissionList = permissionList;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	// stulist和crlist相互引用对方，导致你调用tostring的时候无限递归。去掉两者之一对对方的引用。
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleId=" + roleId + ", roleName=" + roleName + ", description=" + description
				+ ", status=" + status + ", permissionList=" + permissionList + "]";
	}

}