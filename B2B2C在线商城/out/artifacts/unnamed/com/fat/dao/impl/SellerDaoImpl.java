package com.fat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import com.fat.dao.SellerDao;
import com.fat.pojo.SellerBean;
import com.fat.util.JDBCPoolUtil;

public class SellerDaoImpl implements SellerDao {

	@Override
	public boolean query(String username, String password) {
boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select S_username,S_password from tb_seller where S_username = ? and S_password = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1,username);
			pst.setString(2, password);
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
	public HashMap<Integer, SellerBean> queryAll() {
		
		HashMap<Integer, SellerBean> result = new HashMap<Integer,SellerBean>();
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rSet= null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select * from tb_seller";
			pStatement = conn.prepareStatement(sql);
			rSet = pStatement.executeQuery();
			int i = 0;
			while (rSet.next()) {
				int id = rSet.getInt("S_id");
				String name = rSet.getString("S_username");
				String pwd = rSet.getString("S_password");
				String phone = rSet.getString("S_phone");
				String nickname = rSet.getString("S_nickname");
				String time = rSet.getString("S_createtime");
				String head = rSet.getString("S_headportrait");
				String desc = rSet.getString("S_intro");
				SellerBean bean = new SellerBean(id, name, pwd, phone, nickname, time, head, desc);
				result.put(i, bean);
				i ++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pStatement, rSet);
		}
		
		return result;
	}

	@Override
	public boolean insert(SellerBean bean) {
	
		String username = bean.getS_username();
		String password = bean.getS_password();
		String phone = bean.getS_phone();
		String nickname = bean.getS_nickname();
		Random random = new Random();
		int head = random.nextInt(30) + 1;
		String headportrait = "headimages/" + head  + ".jpg";
		String intro = bean.getS_intro();
		
		Connection conn = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "insert into tb_seller value (null,?,?,?,?,null,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, phone);
			pst.setString(4, nickname);
			pst.setString(5, headportrait);
			pst.setString(6, intro);
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		if (result > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(int id) {
	
		Connection conn = null;
		PreparedStatement pst = null;
		int result = 0;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "delete from tb_seller where S_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCPoolUtil.release(conn, pst);
		}
		
		if (result > 0) 
			return true;
		else 
			return false;		
	}

	@Override
	public boolean update(SellerBean bean) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		int result = 0;
		
		int id = bean.getS_id();
		String username = bean.getS_username();
		String password = bean.getS_password();
		String phone = bean.getS_phone();
		String nickname = bean.getS_nickname();
		String createtime = bean.getS_createtime();
		String headportrait = bean.getS_headportrait();
		String intro = bean.getS_intro();
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "update tb_seller set S_username=?,S_password=?,S_phone=?,"
					+ "S_nickname=?,S_createtime=?,S_headportrait=?,S_intro=? where S_id=?";
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, phone);
			pst.setString(4, nickname);
			pst.setString(5, createtime);
			pst.setString(6, headportrait);
			pst.setString(7, intro);
			pst.setInt(8, id);
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCPoolUtil.release(conn, pst);
		}
		
		if (result > 0)
			return true;
		else
			return false;
	}

	@Override
	public SellerBean queryLogin(String username, String password) {
		
		SellerBean bean = null;
		
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rSet= null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select * from tb_seller where S_username = ? and S_password = ?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			rSet = pStatement.executeQuery();
			
			while (rSet.next()) {
				int id = rSet.getInt("S_id");
				String name = rSet.getString("S_username");
				String pwd = rSet.getString("S_password");
				String phone = rSet.getString("S_phone");
				String nickname = rSet.getString("S_nickname");
				String time = rSet.getString("S_createtime");
				String head = rSet.getString("S_headportrait");
				String desc = rSet.getString("S_intro");
				bean = new SellerBean(id, name, pwd, phone, nickname, time, head, desc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pStatement, rSet);
		}
		
		return bean;
	}

	

}
