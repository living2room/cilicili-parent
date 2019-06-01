package com.cilicili.payment.domain;


public class VipType {
//	VipID	int
	private Integer VipID;
//	VipPrice	decimal
	private double VipPrice;
//	VipName	varchar
	private String VipName;
//	VipDescribe	varchar
	private String VipDescribe;
//	AddVipTime	datetime
	private double VipTime;
	public VipType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VipType(Integer vipID, double vipPrice, String vipName, String vipDescribe, double vipTime) {
		super();
		VipID = vipID;
		VipPrice = vipPrice;
		VipName = vipName;
		VipDescribe = vipDescribe;
		VipTime = vipTime;
	}
	public Integer getVipID() {
		return VipID;
	}
	public void setVipID(Integer vipID) {
		VipID = vipID;
	}
	public double getVipPrice() {
		return VipPrice;
	}
	public void setVipPrice(double vipPrice) {
		VipPrice = vipPrice;
	}
	public String getVipName() {
		return VipName;
	}
	public void setVipName(String vipName) {
		VipName = vipName;
	}
	public String getVipDescribe() {
		return VipDescribe;
	}
	public void setVipDescribe(String vipDescribe) {
		VipDescribe = vipDescribe;
	}
	public double getVipTime() {
		return VipTime;
	}
	public void setVipTime(double vipTime) {
		VipTime = vipTime;
	}
	@Override
	public String toString() {
		return "VipType [VipID=" + VipID + ", VipPrice=" + VipPrice + ", VipName=" + VipName + ", VipDescribe="
				+ VipDescribe + ", VipTime=" + VipTime + "]";
	}
}
