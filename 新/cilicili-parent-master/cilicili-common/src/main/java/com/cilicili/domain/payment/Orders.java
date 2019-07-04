package com.cilicili.domain.payment;

public class Orders {
//	ID	int
	private Integer ID;
//	price	double
	private double price;
//	vipType	int
	private Integer vipType;
//	paymentID	bigint
	private Long paymentID;
//	userID	varchar
	private String userID;
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Orders(Integer iD, double price, Integer vipType, Long paymentID, String userID) {
		super();
		ID = iD;
		this.price = price;
		this.vipType = vipType;
		this.paymentID = paymentID;
		this.userID = userID;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getVipType() {
		return vipType;
	}
	public void setVipType(Integer vipType) {
		this.vipType = vipType;
	}
	public Long getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(Long paymentID) {
		this.paymentID = paymentID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	@Override
	public String toString() {
		return "Order [ID=" + ID + ", price=" + price + ", vipType=" + vipType + ", paymentID=" + paymentID
				+ ", userID=" + userID + "]";
	}

	
}
