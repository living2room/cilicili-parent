package com.cilicili.domain.payment;

public class RedPackets {
	private Integer redPacketsID;
	private String redPacketsName;
	private String redPacketsDescribe;
	private Double redPacketsPrice;
	private Integer userID; //领取的用户ID；
	public RedPackets() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RedPackets(Integer redPacketsID, String redPacketsName, String redPacketsDescribe, Double redPacketsPrice,
			Integer status, Integer userID) {
		super();
		this.redPacketsID = redPacketsID;
		this.redPacketsName = redPacketsName;
		this.redPacketsDescribe = redPacketsDescribe;
		this.redPacketsPrice = redPacketsPrice;
		this.userID = userID;
	}
	public Integer getRedPacketsID() {
		return redPacketsID;
	}
	public void setRedPacketsID(Integer redPacketsID) {
		this.redPacketsID = redPacketsID;
	}
	public String getRedPacketsName() {
		return redPacketsName;
	}
	public void setRedPacketsName(String redPacketsName) {
		this.redPacketsName = redPacketsName;
	}
	public String getRedPacketsDescribe() {
		return redPacketsDescribe;
	}
	public void setRedPacketsDescribe(String redPacketsDescribe) {
		this.redPacketsDescribe = redPacketsDescribe;
	}
	public Double getRedPacketsPrice() {
		return redPacketsPrice;
	}
	public void setRedPacketsPrice(Double redPacketsPrice) {
		this.redPacketsPrice = redPacketsPrice;
	}

	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	@Override
	public String toString() {
		return "RedPackets [redPacketsID=" + redPacketsID + ", redPacketsName=" + redPacketsName
				+ ", redPacketsDescribe=" + redPacketsDescribe + ", redPacketsPrice=" + redPacketsPrice + ", userID="
				+ userID + "]";
	}
}
