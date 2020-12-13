package com.fat.dao;

import java.util.List;

import com.fat.pojo.ProductBean;

public interface ProductDao {
	public ProductBean queryById(int id);
	public List<ProductBean> queryByKeyword(String key);
	public List<ProductBean> queryAll();
	
	public List<ProductBean>queryType(int type);
	public List<ProductBean>queryStore(int St_no);
	public double getPrice (int P_id);
	public boolean update (ProductBean bean);
	public boolean delete (int P_id);
	public boolean sale(int count,int P_id);
}
