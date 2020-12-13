package com.fat.dao;

import com.fat.pojo.StoreBean;

public interface  StoreDao {
	
	public StoreBean queryById(int st_id);
	public boolean insert(StoreBean bean);
	public boolean delete(int st_id);
	public boolean update(StoreBean bean);
	

}
