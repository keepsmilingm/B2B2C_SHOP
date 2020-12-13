package com.fat.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class ProductCarBackBean {
	@JSONField(name="Pc_count")
	private int Pc_count;
	@JSONField(name="Sc_id")
	private int Sc_id;
	ProductBean bean;
	
	public ProductCarBackBean(int pc_count, ProductBean bean,int sc_id) {
		Pc_count = pc_count;
		this.bean = bean;
		Sc_id = sc_id;
	}
	
	public int getSc_id() {
		return Sc_id;
	}
	public void setSc_id(int sc_id) {
		Sc_id = sc_id;
	}
	public int getPc_count() {
		return Pc_count;
	}
	public void setPc_count(int pc_count) {
		Pc_count = pc_count;
	}
	public ProductBean getBean() {
		return bean;
	}
	public void setBean(ProductBean bean) {
		this.bean = bean;
	}
}
