package com.fat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fat.dao.ProductDao;
import com.fat.pojo.ProductBean;
import com.fat.util.JDBCPoolUtil;

public class ProductDaoImpl implements ProductDao{

	@Override
	public ProductBean queryById(int id) {

		ProductBean bean = null;
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select * from tb_product where P_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				int P_id = rs.getInt("P_id");
				String P_name = rs.getString("P_name");
				double P_price = rs.getDouble("P_price");
				String P_uptime = rs.getString("P_uptime");
				String P_image = rs.getString("P_image");
				String P_desc = rs.getString("P_desc");
				int P_inventory = rs.getInt("P_inventory");
				int C_no = rs.getInt("C_no");
				int St_no = rs.getInt("St_no");
				bean = new ProductBean(P_id, P_name, P_price, P_uptime, P_image, P_desc, P_inventory, C_no, St_no);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst,rs);
		}
		return bean;
	}

	@Override
	public List<ProductBean> queryType(int type) {
List<ProductBean> list = new ArrayList<ProductBean>();
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select * from tb_product where C_no = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, type);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				int P_id = rs.getInt("P_id");
				String P_name = rs.getString("P_name");
				double P_price = rs.getDouble("P_price");
				String P_uptime = rs.getString("P_uptime");
				String P_image = rs.getString("P_image");
				String P_desc = rs.getString("p_desc");
				int P_inventory = rs.getInt("P_inventory");
				int C_no = rs.getInt("C_no");
				int St_no = rs.getInt("St_no");

				ProductBean bean = new ProductBean(P_id, P_name, P_price, P_uptime, P_image, P_desc, P_inventory, C_no, St_no);
				
				list.add(bean);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst, rs);
		}
		return list;
	}
	
	@Override
	public List<ProductBean> queryStore(int Store_id) {
List<ProductBean> list = new ArrayList<ProductBean>();
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select * from tb_product pro ,tb_store st where pro.St_no=st.St_id and st.St_id=?";
//			String sql = "select * from view_store_product v where v.S_id=?";
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Store_id);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				int P_id = rs.getInt("P_id");
				String P_name = rs.getString("P_name");
				double P_price = rs.getDouble("P_price");
				String P_uptime = rs.getString("P_uptime");
				String P_image = rs.getString("P_image");
				String P_desc = rs.getString("p_desc");
				String St_name = rs.getString("St_name");
				System.out.println(St_name);
				int P_inventory = rs.getInt("P_inventory");
				int C_no = rs.getInt("C_no");
				int St_no = rs.getInt("St_no");

				ProductBean bean = new ProductBean(P_id, P_name, P_price, P_uptime, P_image, P_desc, P_inventory, C_no, St_no,St_name);
				
				list.add(bean);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst, rs);
		}
		return list;
	}
	
	@Override
	public List<ProductBean> queryAll() {

		List<ProductBean> list = new ArrayList<ProductBean>();
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select * from tb_product";
			pst = conn.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				int P_id = rs.getInt("P_id");
				String P_name = rs.getString("P_name");
				double P_price = rs.getDouble("P_price");
				String P_uptime = rs.getString("P_uptime");
				String P_image = rs.getString("P_image");
				String P_desc = rs.getString("p_desc");
				int P_inventory = rs.getInt("P_inventory");
				int C_no = rs.getInt("C_no");
				int St_no = rs.getInt("St_no");

				ProductBean bean = new ProductBean(P_id, P_name, P_price, P_uptime, P_image, P_desc, P_inventory, C_no, St_no);
				
				list.add(bean);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst, rs);
		}
		return list;
	}

	@Override
	public double getPrice(int P_id) {
		double price = 0;
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select P_price from tb_product where P_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, P_id);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				price = rs.getDouble("P_price");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
	
		return price;
	}

	@Override
	public boolean sale(int count,int P_id) {
		Connection conn = null;
		PreparedStatement pst = null;
		int rs=0;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "update tb_product set P_inventory = P_inventory - ? where P_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, count);
			pst.setInt(2, P_id);
			rs=pst.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		if(rs > 0) 
			return true;
		else
			return false;
	}
	
	public boolean update(ProductBean bean) {
		int P_id=bean.getP_id();
		String P_name=bean.getP_name();

		String P_desc=bean.getP_desc();
		Double P_price=bean.getP_price();
		int P_inventory=bean.getP_inventory();
		Connection conn = null;
		PreparedStatement pst = null;
		int rs=0;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "update tb_product set P_inventory =?,P_name=?,P_desc=?,P_price=? where P_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, P_inventory);
			pst.setString(2, P_name);
			pst.setString(3, P_desc); 
			pst.setDouble(4, P_price);
			pst.setInt(5, P_id);
			rs=pst.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		if(rs > 0) 
			return true;
		else
			return false;
		
	}

	@Override
	public boolean delete(int P_id) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		int rs=0;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "delete from tb_product where P_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, P_id);
			rs=pst.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		if(rs > 0) 
			return true;
		else
			return false;
	}

	@Override
	public List<ProductBean> queryByKeyword(String key) {
		List<ProductBean> list = new ArrayList<ProductBean>();
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select *from tb_product pro where  concat(P_name,P_desc) like '%"+key+"%'";
			pst = conn.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				int P_id = rs.getInt("P_id");
				String P_name = rs.getString("P_name");
				double P_price = rs.getDouble("P_price");
				String P_uptime = rs.getString("P_uptime");
				String P_image = rs.getString("P_image");
				String P_desc = rs.getString("p_desc");
				int P_inventory = rs.getInt("P_inventory");
				int C_no = rs.getInt("C_no");
				int St_no = rs.getInt("St_no");

				ProductBean bean = new ProductBean(P_id, P_name, P_price, P_uptime, P_image, P_desc, P_inventory, C_no, St_no);
				
				list.add(bean);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst, rs);
		}
		return list;
		
	}
	
	
}
