package com.cilicili.domain.payment;

public class VipEndTime {
//	ID	int
	private Integer ID;
//	userID	int
	private String userID;
//	vipEndTime	bigint
	private Long vipEndTime;
	public VipEndTime() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VipEndTime(Integer iD, String userID, Long vipEndTime) {
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
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
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
