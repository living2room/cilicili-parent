package com.cilicili.social.domain;

public class MessageBox {
//	message_id	int
	private int message_id;
//	message_title	text
	private String message_title;
//	message_content	text
	private String message_content;
//	sent_userid	varchar
	private String sent_userid;
//	recieve_userid	varchar
	private String recieve_userid;
//	delete_by_sent	int
	private int delete_by_sent;
//	delete_by_reveice	int
	private int delete_by_reveice;
	
	///////////////////////////////////
	private String user_name;

	public MessageBox() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageBox(int message_id, String message_title, String message_content, String sent_userid,
			String recieve_userid, int delete_by_sent, int delete_by_reveice, String user_name) {
		super();
		this.message_id = message_id;
		this.message_title = message_title;
		this.message_content = message_content;
		this.sent_userid = sent_userid;
		this.recieve_userid = recieve_userid;
		this.delete_by_sent = delete_by_sent;
		this.delete_by_reveice = delete_by_reveice;
		this.user_name = user_name;
	}

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public String getMessage_title() {
		return message_title;
	}

	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public String getSent_userid() {
		return sent_userid;
	}

	public void setSent_userid(String sent_userid) {
		this.sent_userid = sent_userid;
	}

	public String getRecieve_userid() {
		return recieve_userid;
	}

	public void setRecieve_userid(String recieve_userid) {
		this.recieve_userid = recieve_userid;
	}

	public int getDelete_by_sent() {
		return delete_by_sent;
	}

	public void setDelete_by_sent(int delete_by_sent) {
		this.delete_by_sent = delete_by_sent;
	}

	public int getDelete_by_reveice() {
		return delete_by_reveice;
	}

	public void setDelete_by_reveice(int delete_by_reveice) {
		this.delete_by_reveice = delete_by_reveice;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Override
	public String toString() {
		return "MessageBox [message_id=" + message_id + ", message_title=" + message_title + ", message_content="
				+ message_content + ", sent_userid=" + sent_userid + ", recieve_userid=" + recieve_userid
				+ ", delete_by_sent=" + delete_by_sent + ", delete_by_reveice=" + delete_by_reveice + ", user_name="
				+ user_name + "]";
	}
	
}
