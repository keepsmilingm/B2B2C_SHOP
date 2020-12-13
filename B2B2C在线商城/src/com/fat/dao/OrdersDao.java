package com.fat.dao;

import java.util.List;

import com.fat.pojo.OrdersBean;

public interface OrdersDao {
	public List<OrdersBean> queryAll();
	public boolean insert (OrdersBean bean);
	public boolean updateState (int O_id,int O_state);
	public boolean delete(int O_id);
	public boolean updateTotal (int O_id,double O_total);
	public int getId();
}
