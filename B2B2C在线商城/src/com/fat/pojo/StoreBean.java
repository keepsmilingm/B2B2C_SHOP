package com.fat.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class StoreBean {
	@JSONField (name="St_id")
	private int St_id;
	@JSONField (name="St_name")
	private String St_name;
	@JSONField (name="St_desc")
	private String St_desc;
	@JSONField (name="S_id")
	private int S_id;
	
	public StoreBean(int St_id, String St_name, String St_desc) {
		this.St_id = St_id;
		this.St_name = St_name;
		this.St_desc = St_desc;

	}

	public int getSt_id() {
		return St_id;
	}

	public void setSt_id(int st_id) {
		St_id = st_id;
	}

	public String getSt_name() {
		return St_name;
	}

	public void setSt_name(String st_name) {
		St_name = st_name;
	}

	public String getSt_desc() {
		return St_desc;
	}

	public void setSt_desc(String st_desc) {
		St_desc = st_desc;
	}

	public int getS_id() {
		return S_id;
	}

	public void setS_id(int s_id) {
		S_id = s_id;
	}
	
}
