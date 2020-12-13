package com.fat.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class BuyerBean {
	@JSONField (name="B_id")
	private Integer B_id;
	@JSONField (name="B_username")
	private String B_username;
	@JSONField (name="B_password")
	private String B_password;
	@JSONField (name="B_phone")
	private String B_phone;
	@JSONField (name="B_nickname")
	private String B_nickname;
	@JSONField (name="B_createtime")
	private String B_createtime;
	@JSONField (name="B_headportrait")
	private String B_headportrait;
	@JSONField (name="B_intro")
	private String B_intro;
	
	public BuyerBean(Integer B_id, String B_username, String B_password, String B_phone, String B_nickname, String B_createtime,
			String B_headportrait, String B_intro) {
		this.B_id = B_id;
		this.B_username = B_username;
		this.B_password = B_password;
		this.B_phone = B_phone;
		this.B_nickname = B_nickname;
		this.B_createtime = B_createtime;
		this.B_headportrait = B_headportrait;
		this.B_intro = B_intro;
	}

	public Integer getB_id() {
		return B_id;
	}

	public void setB_id(Integer b_id) {
		B_id = b_id;
	}

	public String getB_username() {
		return B_username;
	}

	public void setB_username(String b_username) {
		B_username = b_username;
	}

	public String getB_password() {
		return B_password;
	}

	public void setB_password(String b_password) {
		B_password = b_password;
	}

	public String getB_phone() {
		return B_phone;
	}

	public void setB_phone(String b_phone) {
		B_phone = b_phone;
	}

	public String getB_nickname() {
		return B_nickname;
	}

	public void setB_nickname(String b_nickname) {
		B_nickname = b_nickname;
	}

	public String getB_createtime() {
		return B_createtime;
	}

	public void setB_createtime(String b_createtime) {
		B_createtime = b_createtime;
	}

	public String getB_headportrait() {
		return B_headportrait;
	}

	public void setB_headportrait(String b_headportrait) {
		B_headportrait = b_headportrait;
	}

	public String getB_intro() {
		return B_intro;
	}

	public void setB_intro(String b_intro) {
		B_intro = b_intro;
	}
	
}
