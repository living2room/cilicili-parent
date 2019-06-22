package com.cilicili.domain.payment;


public class BuyVip {
//	VipID	int
	private Integer VipID;
//	VipPrice	decimal
	private double VipPrice;
//	VipName	varchar
	private String VipName;
//	VipDescribe	varchar
	private String VipDescribe;
//	VipTime	double
	private double VipTime;
//	discountID	int
	private Integer discountID;
//	discountName	varchar
	private String discountName;
//	vipTypeID	int
	private Integer vipTypeID;
//	discountValue	int
	private Integer discountValue;
	public BuyVip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BuyVip(Integer vipID, double vipPrice, String vipName, String vipDescribe, double vipTime,
			Integer discountID, String discountName, Integer vipTypeID, Integer discountValue) {
		super();
		VipID = vipID;
		VipPrice = vipPrice;
		VipName = vipName;
		VipDescribe = vipDescribe;
		VipTime = vipTime;
		this.discountID = discountID;
		this.discountName = discountName;
		this.vipTypeID = vipTypeID;
		this.discountValue = discountValue;
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
	public Integer getDiscountID() {
		return discountID;
	}
	public void setDiscountID(Integer discountID) {
		this.discountID = discountID;
	}
	public String getDiscountName() {
		return discountName;
	}
	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}
	public Integer getVipTypeID() {
		return vipTypeID;
	}
	public void setVipTypeID(Integer vipTypeID) {
		this.vipTypeID = vipTypeID;
	}
	public Integer getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(Integer discountValue) {
		this.discountValue = discountValue;
	}
	@Override
	public String toString() {
		return "BuyVip [VipID=" + VipID + ", VipPrice=" + VipPrice + ", VipName=" + VipName + ", VipDescribe="
				+ VipDescribe + ", VipTime=" + VipTime + ", discountID=" + discountID + ", discountName=" + discountName
				+ ", vipTypeID=" + vipTypeID + ", discountValue=" + discountValue + "]";
	}
}
