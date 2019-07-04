package com.cilicilil.domain.social;

public class Tb_subscribe {
//	id	int
	private int id;
//	user_id	varchar
	private String user_id;
//	to_user_id	varchar
	private String to_user_id;
	public Tb_subscribe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tb_subscribe(int id, String user_id, String to_user_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.to_user_id = to_user_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTo_user_id() {
		return to_user_id;
	}
	public void setTo_user_id(String to_user_id) {
		this.to_user_id = to_user_id;
	}
	@Override
	public String toString() {
		return "Tb_subscribe [id=" + id + ", user_id=" + user_id + ", to_user_id=" + to_user_id + "]";
	}
	
	

}
