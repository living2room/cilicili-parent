package com.cilicili.user.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cilicili.domain.user.admin.AdminPermission;

@Mapper
public interface AdminPermissionMapper {

	//根据父节点查二级菜单
	public List<AdminPermission> Two(int parentId); 
}