package com.fat.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class AdminBean {
	@JSONField (name="Ad_id")
	private Integer Ad_id;
	@JSONField (name="Ad_username")
	private String Ad_username;
	@JSONField (name="Ad_password")
	private String Ad_password;
	@JSONField (name="Ad_nickname")
	private String Ad_nickname;
	public Integer getAd_id() {
		return Ad_id;
	}
	public void setAd_id(Integer ad_id) {
		Ad_id = ad_id;
	}
	public String getAd_username() {
		return Ad_username;
	}
	public void setAd_username(String ad_username) {
		Ad_username = ad_username;
	}
	public String getAd_password() {
		return Ad_password;
	}
	public void setAd_password(String ad_password) {
		Ad_password = ad_password;
	}
	public String getAd_nickname() {
		return Ad_nickname;
	}
	public void setAd_nickname(String ad_nickname) {
		Ad_nickname = ad_nickname;
	}
	public AdminBean(Integer ad_id, String ad_username, String ad_password, String ad_nickname) {
		super();
		Ad_id = ad_id;
		Ad_username = ad_username;
		Ad_password = ad_password;
		Ad_nickname = ad_nickname;
	}
	
	
}
