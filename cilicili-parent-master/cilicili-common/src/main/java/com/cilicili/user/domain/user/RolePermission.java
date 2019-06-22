package com.cilicili.user.domain.user;

public class RolePermission {
	
	//id	int	
    private Integer id;
    
   // role_id	varchar	角色id
    private String roleId;
    
    //permission_id 权限id
    private String permissionId;

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

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId == null ? null : permissionId.trim();
    }
}