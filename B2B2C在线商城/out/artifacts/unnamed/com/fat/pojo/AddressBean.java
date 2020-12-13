package com.fat.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class AddressBean {
	@JSONField (name="A_id")
	private int A_id;
	@JSONField (name="A_address")
	private String A_address;
	@JSONField (name="B_foreign")
	private int B_foreign;
	
	public AddressBean(int a_id, String a_address, int b_foreign) {
		super();
		A_id = a_id;
		A_address = a_address;
		B_foreign = b_foreign;
	}
	
	public int getA_id() {
		return A_id;
	}
	
	public void setA_id(int a_id) {
		A_id = a_id;
	}
	
	public String getA_address() {
		return A_address;
	}
	
	public void setA_address(String a_address) {
		A_address = a_address;
	}
	
	public int getB_foreign() {
		return B_foreign;
	}
	
	public void setB_foreign(int b_foreign) {
		B_foreign = b_foreign;
	}

}
