package com.cilicili.common.dto;

public class FacePageResponse {
	
	private String error_code;
	private String error_msg;
	private String user_info;
	private double score;
	
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public String getUser_info() {
		return user_info;
	}
	public void setUser_info(String user_info) {
		this.user_info = user_info;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "FacePageResponse [error_code=" + error_code + ", error_msg=" + error_msg + ", user_info=" + user_info
				+ ", score=" + score + "]";
	}
}
