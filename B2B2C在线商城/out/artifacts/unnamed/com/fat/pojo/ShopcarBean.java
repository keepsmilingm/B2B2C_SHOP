package com.fat.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class ShopcarBean {
	@JSONField(name="Sc_id")
	private int Sc_id;
	@JSONField(name="B_no")
	private int B_no;
	
	public ShopcarBean(int sc_id, int b_no) {
		Sc_id = sc_id;
		B_no = b_no;
	}
	public int getSc_id() {
		return Sc_id;
	}
	public void setSc_id(int sc_id) {
		Sc_id = sc_id;
	}
	public int getB_no() {
		return B_no;
	}
	public void setB_no(int b_no) {
		B_no = b_no;
	}
}
