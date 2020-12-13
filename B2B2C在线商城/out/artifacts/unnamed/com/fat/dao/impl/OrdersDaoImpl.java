package com.fat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fat.dao.OrdersDao;
import com.fat.pojo.OrdersBean;
import com.fat.util.JDBCPoolUtil;

public class OrdersDaoImpl implements OrdersDao{

	@Override
	public List<OrdersBean> queryAll() {

		List <OrdersBean> result = new ArrayList<OrdersBean>();
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select * from tb_orders";
			pst = conn.prepareStatement(sql);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("O_id");
				String bname = rs.getString("O_bname");
				String bphone = rs.getString("O_bphone");
				String time = rs.getString("O_time");
				String address = rs.getString("O_address");
				int state = rs.getInt("O_state");
				double price = rs.getDouble("P_price");
				int bid = rs.getInt("B_no");
				OrdersBean bean = new OrdersBean(id, bname, bphone, time, address, state,price, bid);
				result.add(bean);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCPoolUtil.release(conn, pst,rs);
		}
		
		return result;
	}

	@Override
	public boolean insert(OrdersBean bean) {
		
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pst = null;
		int result = 0;
		
		String O_bname = bean.getO_bname();
		String O_bphone = bean.getO_bphone();
		String O_address = bean.getO_address();
		int B_no = bean.getB_no();
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "insert into tb_orders value(null,?,?,null,?,0,0,?)";
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, O_bname);
			pst.setString(2, O_bphone);
			pst.setString(3, O_address);
			pst.setInt(4, B_no);
			
			result = pst.executeUpdate();
			
			if (result > 0) {
				flag = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		
		return flag;
	}

	@Override
	public boolean updateState(int O_id,int O_state) {

		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pst = null;
		int result = 0;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "update tb_order set O_state = ? where O_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, O_state);
			pst.setInt(2, O_id);
			
			result = pst.executeUpdate();
			
			if (result > 0) {
				flag = true;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		
		return flag;
	}

	@Override
	public boolean delete(int O_id) {
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pst = null;
		int result = 0;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "delete from tb_orders where O_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, O_id);
			
			result = pst.executeUpdate();
			
			if (result > 0) {
				flag = true;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		
		return flag;
	}

	@Override
	public boolean updateTotal(int O_id, double O_total) {

		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pst = null;
		int result = 0;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "update tb_orders set O_total = ? where O_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setDouble(1, O_total);
			pst.setInt(2, O_id);
			
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
	public int getId() {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select O_no from tb_ordersitem orders O_no desc limit 1";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				result = rs.getInt("O_no");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst,rs);
		}
		
		return result;
	}
	
}
