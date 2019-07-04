package com.cilicili.domain.user.admin;

public class AdminRolePermission {

	// id int
	private Integer id;

	// role_id varchar 角色id
	private Integer roleId;

	// permission_id 权限id
	private Integer permissionId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public AdminRolePermission(Integer id, Integer roleId, Integer permissionId) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public AdminRolePermission() {
		super();
		// TODO Auto-generated constructor stub
	}

}