package com.cilicili.domain.user.user;

import java.util.Date;

public class UsersMessage {

	private String userId;
	private String userName;
	private String phone;
	private int sex;
	private String home;
	private int age;
	private String idCard;
	private String qq;
	private String imgUrl;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public UsersMessage(String userId, String userName, String phone, int sex, String home, int age, String idCard,
			String qq, String imgUrl) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.phone = phone;
		this.sex = sex;
		this.home = home;
		this.age = age;
		this.idCard = idCard;
		this.qq = qq;
		this.imgUrl = imgUrl;
	}

	public UsersMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UsersMessage [userId=" + userId + ", userName=" + userName + ", phone=" + phone + ", sex=" + sex
				+ ", home=" + home + ", age=" + age + ", idCard=" + idCard + ", qq=" + qq + ", imgUrl=" + imgUrl + "]";
	}

}
