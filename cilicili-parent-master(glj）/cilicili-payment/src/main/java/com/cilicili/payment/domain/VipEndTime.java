package com.cilicili.payment.domain;

public class VipEndTime {
//	ID	int
	private Integer ID;
//	userID	int
	private Integer userID;
//	vipEndTime	bigint
	private Long vipEndTime;
	public VipEndTime() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VipEndTime(Integer iD, Integer userID, Long vipEndTime) {
		super();
		ID = iD;
		this.userID = userID;
		this.vipEndTime = vipEndTime;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Long getVipEndTime() {
		return vipEndTime;
	}
	public void setVipEndTime(Long vipEndTime) {
		this.vipEndTime = vipEndTime;
	}
	@Override
	public String toString() {
		return "VipEndTime [ID=" + ID + ", userID=" + userID + ", vipEndTime=" + vipEndTime + "]";
	}
 
}
