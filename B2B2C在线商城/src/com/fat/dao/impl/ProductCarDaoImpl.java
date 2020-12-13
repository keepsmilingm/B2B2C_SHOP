package com.fat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fat.dao.ProductCarDao;
import com.fat.pojo.ProductBean;
import com.fat.pojo.ProductCarBackBean;
import com.fat.util.JDBCPoolUtil;

public class ProductCarDaoImpl implements ProductCarDao {

	@Override
	public boolean insert(int Sc_id, int P_id) {

		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			
			if (isExist(P_id,Sc_id)) {
				updateCount(P_id);
				flag = true;
			} else {
				String sql = "insert into tb_productcar value(?,?,0,?)";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, Sc_id);
				pst.setInt(2, P_id);
				pst.setInt(3, 1);
				int result = pst.executeUpdate();
				if (result > 0)
					flag = true;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCPoolUtil.release(conn, pst);
		}
		
		return flag;
	}

	@Override
	public void updateState(int Pc_state) {
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "update tb_productcar set Pc_state = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Pc_state);
			pst.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCPoolUtil.release(conn, pst);
		}
	}

	@Override
	public List<ProductCarBackBean> getProduct(int Sc_id) {

		List<ProductCarBackBean> list = new ArrayList<ProductCarBackBean>();
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select P_no from tb_productcar where Sc_no = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Sc_id);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				int P_id = rs.getInt("P_no");
				ProductDaoImpl pImpl = new ProductDaoImpl();
				ProductBean bean = pImpl.queryById(P_id);
				
				ProductCarBackBean bean1 = new ProductCarBackBean(getCount(P_id), bean,Sc_id);
				list.add(bean1);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst,rs);
		}
		
		return list;
	}

	@Override
	public boolean isExist(int P_no,int Sc_no) {
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select * from tb_productcar where P_no = ? and Sc_no = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, P_no);
			pst.setInt(2, Sc_no);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				flag = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCPoolUtil.release(conn, pst, rs);
		}
		return flag;
	}

	@Override
	public int getCount(int P_no) {
		
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select Pc_count from tb_productcar where P_no = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, P_no);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				count = rs.getInt("Pc_count");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst, rs);
		}
		return count;
		
	}

	@Override
	public boolean updateCount(int P_no) {

		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "update tb_productcar set Pc_count = ? where P_no = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, getCount(P_no) + 1);
			pst.setInt(2, P_no);
			pst.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		
		return flag;
	}

}
