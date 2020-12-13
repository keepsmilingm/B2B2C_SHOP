package com.fat.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class ProductCarBean {
	@JSONField(name="Sc_no")
	int Sc_no;
	@JSONField(name="P_no")
	int P_no;
	@JSONField(name="Pc_state")
	int Pc_state;
	@JSONField(name="Pc_count")
	int Pc_count;
	
	public ProductCarBean(int sc_no, int p_no, int pc_state,int pc_count) {
		Sc_no = sc_no;
		P_no = p_no;
		Pc_state = pc_state;
		Pc_count = pc_count;
	}
	
	public int getPc_count() {
		return Pc_count;
	}
	public void setPc_count(int pc_count) {
		Pc_count = pc_count;
	}
	public int getSc_no() {
		return Sc_no;
	}
	public void setSc_no(int sc_no) {
		Sc_no = sc_no;
	}
	public int getP_no() {
		return P_no;
	}
	public void setP_no(int p_no) {
		P_no = p_no;
	}
	public int getPc_state() {
		return Pc_state;
	}
	public void setPc_state(int pc_state) {
		Pc_state = pc_state;
	}
}
