package com.cilicili.user.domain.admin;

public class AdminUserRole {
    private Integer id;

    private Integer userId;

    private Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public AdminUserRole(Integer id, Integer userId, Integer roleId) {
		super();
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
	}

	public AdminUserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

   
}