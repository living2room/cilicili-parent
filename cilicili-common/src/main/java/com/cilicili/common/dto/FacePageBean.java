package com.cilicili.common.dto;
/**
 * 页面参数对象
 */
public class FacePageBean {
	
	private String imgdata;
	private String imgname;
	//用户名称
	private String user_info;
	
	public String getImgdata() {
		return imgdata;
	}
	public void setImgdata(String imgdata) {
		this.imgdata = imgdata;
	}
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public String getUser_info() {
		return user_info;
	}
	public void setUser_info(String user_info) {
		this.user_info = user_info;
	}
	
	@Override
	public String toString() {
		return "FacePageBean [imgdata=" + imgdata + ", imgname=" + imgname + ", user_info=" + user_info + "]";
	}
	
}