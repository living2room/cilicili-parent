package com.cilicili.domain.user.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cilicili.domain.user.user.Role;

public class AdminUser implements Serializable {

	private static final long serialVersionUID = -3667904005872983533L;

	private int id;

	// user_id varchar 用户id
	private int userId;

	// user_name varchar 用户名
	private String userName;

	// user_password varchar 密码
	private String userPassword;

	// locked tinyint 是否锁定
	private int locked;

	// salt varchar加密盐值
	private String salt;

	// email varchar邮箱
	private String email;

	// phone varchar 联系方式
	private String phone;

	// sex int 性别：1男2女
	private Integer sex;

	// age int 年龄
	private Integer age;

	// status int 用户状态：1有效; 2删除
	private Integer status;

	// 一个用户有多个角色
	private List<AdminRole> adminRoleList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<AdminRole> getAdminRoleList() {
		return adminRoleList;
	}

	public void setAdminRoleList(List<AdminRole> adminRoleList) {
		this.adminRoleList = adminRoleList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AdminUser(int id, int userId, String userName, String userPassword, int locked, String salt, String email,
			String phone, Integer sex, Integer age, Integer status, List<AdminRole> adminRoleList) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.locked = locked;
		this.salt = salt;
		this.email = email;
		this.phone = phone;
		this.sex = sex;
		this.age = age;
		this.status = status;
		this.adminRoleList = adminRoleList;
	}

	public AdminUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", userId=" + userId + ", userName=" + userName + ", userPassword="
				+ userPassword + ", locked=" + locked + ", salt=" + salt + ", email=" + email + ", phone=" + phone
				+ ", sex=" + sex + ", age=" + age + ", status=" + status + ", adminRoleList=" + adminRoleList + "]";
	}
}