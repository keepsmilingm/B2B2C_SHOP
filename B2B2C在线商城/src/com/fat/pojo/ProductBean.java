package com.fat.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class ProductBean {
	@JSONField (name="P_id")
	private int P_id;
	@JSONField (name="P_name")
	private String P_name;
	@JSONField (name="P_price")
	private double P_price;
	@JSONField (name="P_uptime")
	private String P_uptime;
	@JSONField (name="P_image")
	private String P_image;
	@JSONField (name="P_desc")
	private String P_desc;
	@JSONField (name="St_name")
	private String St_name;
	@JSONField (name="P_inventory")
	int P_inventory;
	@JSONField (name="C_no")
	private int C_no;
	@JSONField (name="St_no")
	private int St_no;
	
	public int getP_id() {
		return P_id;
	}
	public void setP_id(int p_id) {
		P_id = p_id;
	}
	public String getP_name() {
		return P_name;
	}
	public void setP_name(String p_name) {
		P_name = p_name;
	}
	public double getP_price() {
		return P_price;
	}
	public void setP_price(double p_price) {
		P_price = p_price;
	}
	public String getP_uptime() {
		return P_uptime;
	}
	public void setP_uptime(String p_uptime) {
		P_uptime = p_uptime;
	}
	public String getP_image() {
		return P_image;
	}
	public void setP_image(String p_image) {
		P_image = p_image;
	}
	public String getP_desc() {
		return P_desc;
	}
	public void setP_desc(String st_name) {
		P_desc = st_name;
	}
	public String getSt_name() {
		return St_name;
	}
	public void setSt_name(String st_name) {
		P_desc = st_name;
	}
	public int getP_inventory() {
		return P_inventory;
	}
	public void setP_inventory(int p_inventory) {
		P_inventory = p_inventory;
	}
	public int getC_no() {
		return C_no;
	}
	public void setC_no(int c_no) {
		C_no = c_no;
	}
	public int getSt_no() {
		return St_no;
	}
	public void setSt_no(int st_no) {
		St_no = st_no;
	}
	public ProductBean(int p_id, String p_name, double p_price, String p_uptime, String p_image, String p_desc,
			int p_inventory, int c_no, int st_no) {
		super();
		P_id = p_id;
		P_name = p_name;
		P_price = p_price;
		P_uptime = p_uptime;
		P_image = p_image;
		P_desc = p_desc;
		P_inventory = p_inventory;
		C_no = c_no;
		St_no = st_no;
		
	}
	public ProductBean(int p_id, String p_name, String p_desc, double p_price,int p_inventory) {
		super();
		P_id = p_id;
		P_name = p_name;
		P_price = p_price;
		
		P_desc = p_desc;
		P_inventory = p_inventory;
	
		
	}
	public ProductBean(int p_id, String p_name, double p_price, String p_uptime, String p_image, String p_desc,
			int p_inventory, int c_no, int st_no,String st_name) {
		super();
		P_id = p_id;
		P_name = p_name;
		P_price = p_price;
		P_uptime = p_uptime;
		P_image = p_image;
		P_desc = p_desc;
		P_inventory = p_inventory;
		C_no = c_no;
		St_no = st_no;
		St_name=st_name;
	}
	

}
