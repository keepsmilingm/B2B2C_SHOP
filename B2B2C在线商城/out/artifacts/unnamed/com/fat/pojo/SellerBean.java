package com.fat.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class SellerBean {
	@JSONField (name="S_id")
	private Integer S_id;
	@JSONField (name="S_username")
	private String S_username;
	@JSONField (name="S_password")
	private String S_password;
	@JSONField (name="S_phone")
	private String S_phone;
	@JSONField (name="S_nickname")
	private String S_nickname;
	@JSONField (name="S_createtime")
	private String S_createtime;
	@JSONField (name="S_headportrait")
	private String S_headportrait;
	@JSONField (name="S_intro")
	private String S_intro;
	
	
	
	public SellerBean(Integer S_id, String S_username, String S_password, String S_phone, String S_nickname, String S_createtime,
			String S_headportrait, String S_intro) {
		this.S_id = S_id;
		this.S_username = S_username;
		this.S_password = S_password;
		this.S_phone = S_phone;
		this.S_nickname = S_nickname;
		this.S_createtime = S_createtime;
		this.S_headportrait = S_headportrait;
		this.S_intro = S_intro;
	}

	public Integer getS_id() {
		return S_id;
	}

	public void setS_id(Integer s_id) {
		S_id = s_id;
	}

	public String getS_username() {
		return S_username;
	}

	public void setS_username(String s_username) {
		S_username = s_username;
	}

	public String getS_password() {
		return S_password;
	}

	public void setS_password(String s_password) {
		S_password = s_password;
	}

	public String getS_phone() {
		return S_phone;
	}

	public void setS_phone(String s_phone) {
		S_phone = s_phone;
	}

	public String getS_nickname() {
		return S_nickname;
	}

	public void setS_nickname(String s_nickname) {
		S_nickname = s_nickname;
	}

	public String getS_createtime() {
		return S_createtime;
	}

	public void setS_createtime(String s_createtime) {
		S_createtime = s_createtime;
	}


	public String getS_headportrait() {
		return S_headportrait;
	}

	public void setS_headportrait(String s_headportrait) {
		S_headportrait = s_headportrait;
	}


	public String getS_intro() {
		return S_intro;
	}

	public void setS_intro(String s_intro) {
		S_intro = s_intro;
	}
	
}
