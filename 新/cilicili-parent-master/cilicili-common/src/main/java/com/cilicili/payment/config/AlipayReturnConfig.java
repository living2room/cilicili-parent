package com.cilicili.payment.config;

public class AlipayReturnConfig {
	private String charset;
	private String out_trade_no;
	private String method;
	private String total_amount;
	private String sign;
	private String trade_no;
	private String auth_app_id;
	private String version;
	private String app_id;
	private String sign_type;
	private String seller_id;
	private String timestamp;
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getAuth_app_id() {
		return auth_app_id;
	}
	public void setAuth_app_id(String auth_app_id) {
		this.auth_app_id = auth_app_id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "AlipayReturnConfig [charset=" + charset + ", out_trade_no=" + out_trade_no + ", method=" + method
				+ ", total_amount=" + total_amount + ", sign=" + sign + ", trade_no=" + trade_no + ", auth_app_id="
				+ auth_app_id + ", version=" + version + ", app_id=" + app_id + ", sign_type=" + sign_type
				+ ", seller_id=" + seller_id + ", timestamp=" + timestamp + ", getCharset()=" + getCharset()
				+ ", getOut_trade_no()=" + getOut_trade_no() + ", getMethod()=" + getMethod() + ", getTotal_amount()="
				+ getTotal_amount() + ", getSign()=" + getSign() + ", getTrade_no()=" + getTrade_no()
				+ ", getAuth_app_id()=" + getAuth_app_id() + ", getVersion()=" + getVersion() + ", getApp_id()="
				+ getApp_id() + ", getSign_type()=" + getSign_type() + ", getSeller_id()=" + getSeller_id()
				+ ", getTimestamp()=" + getTimestamp() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public AlipayReturnConfig(String charset, String out_trade_no, String method, String total_amount, String sign,
			String trade_no, String auth_app_id, String version, String app_id, String sign_type, String seller_id,
			String timestamp) {
		super();
		this.charset = charset;
		this.out_trade_no = out_trade_no;
		this.method = method;
		this.total_amount = total_amount;
		this.sign = sign;
		this.trade_no = trade_no;
		this.auth_app_id = auth_app_id;
		this.version = version;
		this.app_id = app_id;
		this.sign_type = sign_type;
		this.seller_id = seller_id;
		this.timestamp = timestamp;
	}
	public AlipayReturnConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
}
