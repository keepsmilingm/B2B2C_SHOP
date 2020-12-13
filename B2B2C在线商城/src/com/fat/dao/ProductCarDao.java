package com.fat.dao;

import java.util.List;

import com.fat.pojo.ProductCarBackBean;

public interface ProductCarDao {
	public boolean insert(int Sc_id,int P_id);
	public void updateState(int Pc_state);
	public List<ProductCarBackBean> getProduct(int Sc_id);
	public boolean isExist (int P_no,int Sc_no);
	public int getCount (int P_no);
	public boolean updateCount(int P_no);
}
