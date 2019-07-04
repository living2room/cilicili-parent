package com.cilicili.domain.payment;

public class OpenVip {
	private Long vipTime;
	private String userID;
	public OpenVip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OpenVip(Long vipTime, String userID) {
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
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	@Override
	public String toString() {
		return "OpenVip [vipTime=" + vipTime + ", userID=" + userID + "]";
	}
	
}
