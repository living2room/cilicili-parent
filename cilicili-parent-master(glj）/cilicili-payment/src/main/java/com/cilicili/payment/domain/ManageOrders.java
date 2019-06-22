package com.cilicili.payment.domain;

public class ManageOrders {
	//  tb_order表
	//	id	int
	//	price	double
	private Double price;
	//	vip_type	int
	//	payment_id	bigint
	private long payment_id;
	//	user_id	int
	private String user_id;

	//tb_already_payment	
	//	id	int
	//	order_id	bigint
	//	alipay_id	text
	private String alipay_id;

	//vip_type
	//	vip_id	int
	//	vip_price	decimal
	//	vip_name	varchar
	private String vip_name;
	//	vip_describe	varchar
	//	vip_time	double

	public ManageOrders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManageOrders(Double price, long payment_id, String user_id, String alipay_id, String vip_name) {
		super();
		this.price = price;
		this.payment_id = payment_id;
		this.user_id = user_id;
		this.alipay_id = alipay_id;
		this.vip_name = vip_name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public long getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(long payment_id) {
		this.payment_id = payment_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAlipay_id() {
		return alipay_id;
	}
	public void setAlipay_id(String alipay_id) {
		this.alipay_id = alipay_id;
	}
	public String getVip_name() {
		return vip_name;
	}
	public void setVip_name(String vip_name) {
		this.vip_name = vip_name;
	}
	@Override
	public String toString() {
		return "ManageOrders [price=" + price + ", payment_id=" + payment_id + ", user_id=" + user_id + ", alipay_id="
				+ alipay_id + ", vip_name=" + vip_name + "]";
	}
}
