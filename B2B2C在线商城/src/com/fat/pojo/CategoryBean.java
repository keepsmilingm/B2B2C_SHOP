package com.fat.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class CategoryBean {
	@JSONField (name="C_id")
	private int C_id;
	@JSONField (name="C_name")
	private String C_name;
	@JSONField (name="C_desc")
	private String C_desc;
	
	public CategoryBean(int C_id, String C_name, String C_desc) {
		this.C_id = C_id;
		this.C_name = C_name;
		this.C_desc = C_desc;
	}

	public int getC_id() {
		return C_id;
	}

	public void setC_id(int c_id) {
		C_id = c_id;
	}

	public String getC_name() {
		return C_name;
	}

	public void setC_name(String c_name) {
		C_name = c_name;
	}

	public String getC_desc() {
		return C_desc;
	}

	public void setC_desc(String c_desc) {
		C_desc = c_desc;
	}
	
	
}
