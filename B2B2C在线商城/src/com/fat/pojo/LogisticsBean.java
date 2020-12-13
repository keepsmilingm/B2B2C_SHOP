package com.fat.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class LogisticsBean {
	@JSONField (name="L_id")
	private int L_id;
	@JSONField (name="L_start")
	private String L_start;
	@JSONField (name="L_current")
	private String L_current;
	@JSONField (name="L_end")
	private String L_end;
	@JSONField (name="O_id")
	private int O_id;
	
	public LogisticsBean(int L_id, String L_start, String L_current, String L_end, int O_id) {
		this.L_id = L_id;
		this.L_start = L_start;
		this.L_current = L_current;
		this.L_end = L_end;
		this.O_id = O_id;
	}

	public int getL_id() {
		return L_id;
	}

	public void setL_id(int l_id) {
		L_id = l_id;
	}

	public String getL_start() {
		return L_start;
	}

	public void setL_start(String l_start) {
		L_start = l_start;
	}

	public String getL_current() {
		return L_current;
	}

	public void setL_current(String l_current) {
		L_current = l_current;
	}

	public String getL_end() {
		return L_end;
	}

	public void setL_end(String l_end) {
		L_end = l_end;
	}

	public int getO_id() {
		return O_id;
	}

	public void setO_id(int o_id) {
		O_id = o_id;
	}
	
}
