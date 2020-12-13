package com.fat.dao;

import java.util.HashMap;

import com.fat.pojo.BuyerBean;

public interface BuyerDao {
	public boolean query(String username,String password);
	public HashMap<Integer,BuyerBean> queryAll();
	public BuyerBean queryById(int B_id);
	public int getIdByName (String B_username);
	public boolean insert(BuyerBean bean);
	public boolean delete(int id);
	public boolean update(BuyerBean bean);
	public BuyerBean queryLogin(String username,String password);
}
