package com.cilicili.payment.domain;

public class OpenVip {
	private Long vipTime;
	private Integer userID;
	public OpenVip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OpenVip(Long vipTime, Integer userID) {
		super();
		this.vipTime = vipTime;
		this.userID = userID;
	}
	public Long getVipTime() {
		return vipTime;
	}
	public void setVipTime(Long vipTime) {
		this.vipTime = vipTime;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	@Override
	public String toString() {
		return "OpenVip [vipTime=" + vipTime + ", userID=" + userID + "]";
	}
	
}
