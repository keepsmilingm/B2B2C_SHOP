package com.fat.dao;

import java.util.HashMap;

import com.fat.pojo.SellerBean;

public interface SellerDao {
	public boolean query(String username,String password);
	public HashMap<Integer,SellerBean> queryAll();
	public boolean insert(SellerBean bean);
	public boolean delete(int id);
	public boolean update(SellerBean bean);
	public SellerBean queryLogin(String username,String password);
}