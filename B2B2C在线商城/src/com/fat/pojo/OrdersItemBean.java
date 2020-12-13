package com.fat.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class OrdersItemBean {
	@JSONField (name="P_no")
	private int P_no;
	@JSONField (name="O_no")
	private int O_no;
	@JSONField (name="OI_count")
	private int OI_count;
	@JSONField (name="OI_sum")
	private double OI_sum;
	
	public OrdersItemBean(int P_no, int O_no, int OI_count, double OI_sum) {
		this.P_no = P_no;
		this.O_no = O_no;
		this.OI_count = OI_count;
		this.OI_sum = OI_sum;
	}

	public int getP_no() {
		return P_no;
	}

	public void setP_no(int p_no) {
		P_no = p_no;
	}

	public int getO_no() {
		return O_no;
	}

	public void setO_no(int o_no) {
		O_no = o_no;
	}

	public int getOI_count() {
		return OI_count;
	}

	public void setOI_count(int oI_count) {
		OI_count = oI_count;
	}

	public double getOI_sum() {
		return OI_sum;
	}

	public void setOI_sum(double oI_sum) {
		OI_sum = oI_sum;
	}
	

}
