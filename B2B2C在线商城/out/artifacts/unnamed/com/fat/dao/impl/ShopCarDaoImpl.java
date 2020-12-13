package com.fat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import com.fat.dao.ShopCarDao;
import com.fat.util.JDBCPoolUtil;


public class ShopCarDaoImpl implements ShopCarDao{

	@Override
	public boolean insert(int B_id) {

		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "insert into tb_shopcar value(?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, B_id);
			pst.setInt(2, B_id);
			
			int result = pst.executeUpdate();
			
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
	public void delete(int B_id, int P_id) {

		
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "delete from tb_productcar where Sc_no = ? and P_no = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, B_id);
			pst.setInt(2, P_id);
			pst.executeUpdate();
			
					
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		
	}

	@Override
	public void delete(int B_id) {
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "delete from tb_productcar where Sc_no = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, B_id);
		
			pst.executeUpdate();
					
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		
	}
	
}
