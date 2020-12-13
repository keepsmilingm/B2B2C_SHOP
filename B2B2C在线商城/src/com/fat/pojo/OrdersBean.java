package com.fat.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class OrdersBean {
	@JSONField (name="O_id")
	private int O_id;						//����ID
	@JSONField (name="O_bname")
	private String O_bname;		//�ջ�������
	@JSONField (name="O_bphone")
	private String O_bphone;	//�ջ��˵绰
	@JSONField (name="O_time")
	private String O_time;			//��������ʱ��
	@JSONField (name="O_address")
	private String O_address;		//�ջ���ַ
	@JSONField (name="O_state")
	private int O_state;					//����״̬,0Ϊδ����,1Ϊ�Ѹ��������,2Ϊ���ջ�,3Ϊ��ǩ��
	@JSONField (name="O_total")
	private double P_price; 
	@JSONField (name="B_no")
	private int B_no;						//���ID

	public OrdersBean(int o_id, String o_bname, String o_bphone, String o_time, String o_address, int o_state,
			double p_price, int b_no) {
		O_id = o_id;
		O_bname = o_bname;
		O_bphone = o_bphone;
		O_time = o_time;
		O_address = o_address;
		O_state = o_state;
		P_price = p_price;
		B_no = b_no;
	}

	public double getP_price() {
		return P_price;
	}

	public void setP_price(double p_price) {
		P_price = p_price;
	}
	
	public int getO_id() {
		return O_id;
	}

	public void setO_id(int o_id) {
		O_id = o_id;
	}

	public String getO_bname() {
		return O_bname;
	}

	public void setO_bname(String o_bname) {
		O_bname = o_bname;
	}

	public String getO_bphone() {
		return O_bphone;
	}

	public void setO_bphone(String o_bphone) {
		O_bphone = o_bphone;
	}

	public String getO_time() {
		return O_time;
	}

	public void setO_time(String o_time) {
		O_time = o_time;
	}

	public String getO_address() {
		return O_address;
	}

	public void setO_address(String o_address) {
		O_address = o_address;
	}

	public int getO_state() {
		return O_state;
	}

	public void setO_state(int o_state) {
		O_state = o_state;
	}

	public int getB_no() {
		return B_no;
	}

	public void setB_no(int b_no) {
		B_no = b_no;
	}
	
}
