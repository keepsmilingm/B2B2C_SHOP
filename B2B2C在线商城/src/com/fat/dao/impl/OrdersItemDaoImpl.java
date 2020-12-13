package com.fat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fat.dao.OrdersItemDao;
import com.fat.pojo.OrdersItemBean;
import com.fat.util.JDBCPoolUtil;

public class OrdersItemDaoImpl implements OrdersItemDao{

	@Override
	public boolean Insert(OrdersItemBean bean) {
		boolean flag = false;

		Connection conn = null;
		PreparedStatement pst = null;
		int result = 0;
		
		int pid = bean.getP_no();
		int oid = bean.getO_no();
		int count = bean.getOI_count();
		double price = new ProductDaoImpl().getPrice(pid) * count;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "insert into tb_ordersitem value(?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pid);
			pst.setInt(2, oid);
			pst.setInt(3, count);
			pst.setDouble(4, price);
			
			result = pst.executeUpdate();
			
			if (result > 0)
				flag = true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		
		return flag;
	}

	@Override
	public boolean delete(int P_no) {
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pst = null;
		int result = 0;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "delete from tb_ordersitem where P_no = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, P_no);
			
			result = pst.executeUpdate();

			if (result > 0)
				flag = true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		
		return flag;

	}

	@Override
	public boolean update(int OI_count,int P_no) {
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pst = null;
		double price = new ProductDaoImpl().getPrice(P_no) * OI_count;
		int result = 0;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "update tb_ordersitem set OI_count = ?,OI_sum = ? where P_no = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, OI_count);
			pst.setDouble(2, price);
			pst.setInt(3, P_no);
			
			result = pst.executeUpdate();
			
			if (result > 0)
				flag = true;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		
		return flag;
	}

	@Override
	public double getTotal(int O_no) {
		double total = 0;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select OI_sum from tb_ordersitem where O_no = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, O_no);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				double price = rs.getDouble("OI_sum");
				total += price;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst,rs);
		}
		new OrdersDaoImpl().updateTotal(O_no, total);
		return total;
	}

	@Override
	public double getOI_Sum(int P_no) {
		double OI_sum = 0;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select OI_sum from tb_ordersitem where P_no = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, P_no);
			rs = pst.executeQuery();
			OI_sum = rs.getDouble("OI_sum");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst,rs);
		}
		return OI_sum;
	}
	
}
