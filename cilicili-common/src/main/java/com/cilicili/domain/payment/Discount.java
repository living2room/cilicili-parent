package com.cilicili.domain.payment;

public class Discount {
	//vip类型的Id
	private Integer vipID;
	//折扣ID
	private Integer discountID;
	//折扣名
	private String discountName;
	//几折
	private Integer discountValue;
	//vipType名称
	private String vipName;
	public Discount(Integer vipID, Integer discountID, String discountName, Integer discountValue, String vipName) {
		super();
		this.vipID = vipID;
		this.discountID = discountID;
		this.discountName = discountName;
		this.discountValue = discountValue;
		this.vipName = vipName;
	}
	public Discount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getVipID() {
		return vipID;
	}
	public void setVipID(Integer vipID) {
		this.vipID = vipID;
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
	public Integer getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(Integer discountValue) {
		this.discountValue = discountValue;
	}
	public String getVipName() {
		return vipName;
	}
	public void setVipName(String vipName) {
		this.vipName = vipName;
	}
	@Override
	public String toString() {
		return "Discount [vipID=" + vipID + ", discountID=" + discountID + ", discountName=" + discountName
				+ ", discountValue=" + discountValue + ", vipName=" + vipName + "]";
	}
	
	
	
}
