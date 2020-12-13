package com.fat.dao;

import com.fat.pojo.OrdersItemBean;

public interface OrdersItemDao {
	public boolean Insert (OrdersItemBean bean);
	public boolean delete(int P_no);
	public boolean update (int OI_count,int P_no);
	public double getTotal(int O_no);
	public double getOI_Sum(int P_no);
}
