package com.fat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import com.fat.dao.BuyerDao;
import com.fat.pojo.BuyerBean;
import com.fat.util.JDBCPoolUtil;

public class BuyerDaoImpl implements BuyerDao{
	
	@Override
	public boolean query(String username,String password) {
		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select B_username,B_password from tb_buyer where B_username = ? and B_password = ?";
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
	public BuyerBean queryById(int B_id) {
		BuyerBean bean = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rSet = null;
		String sql = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
		
			sql = "select * from tb_buyer where B_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, B_id);
			rSet = pst.executeQuery();
			while (rSet.next()) {
				String name = rSet.getString("B_username");
				String pwd = rSet.getString("B_password");
				String phone = rSet.getString("B_phone");
				String nickname = rSet.getString("B_nickname");
				String time = rSet.getString("B_createtime");
				String head = rSet.getString("B_headportrait");
				String desc = rSet.getString("B_intro");
				bean = new BuyerBean(B_id, name, pwd, phone, nickname, time, head, desc);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst,rSet);
		}
		return bean;
	}
	
	@Override
	public HashMap<Integer, BuyerBean> queryAll() {

		HashMap<Integer, BuyerBean> result = new HashMap<Integer,BuyerBean>();
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rSet= null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select * from tb_buyer";
			pStatement = conn.prepareStatement(sql);
			rSet = pStatement.executeQuery();
			int i = 0;
			while (rSet.next()) {
				int id = rSet.getInt("B_id");
				String name = rSet.getString("B_username");
				String pwd = rSet.getString("B_password");
				String phone = rSet.getString("B_phone");
				String nickname = rSet.getString("B_nickname");
				String time = rSet.getString("B_createtime");
				String head = rSet.getString("B_headportrait");
				String desc = rSet.getString("B_intro");
				BuyerBean buyerBean = new BuyerBean(id, name, pwd, phone, nickname, time, head, desc);
				result.put(i, buyerBean);
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
	public boolean insert(BuyerBean bean) {
		
		String username = bean.getB_username();
		String password = bean.getB_password();
		String phone = bean.getB_phone();
		String nickname = bean.getB_nickname();
		Random random = new Random();
		int head = random.nextInt(30) + 1;
		String headportrait = "headimages/" + head  + ".jpg";
		String intro = bean.getB_intro();
		
		Connection conn = null;
		PreparedStatement pst = null;
		int result = 0;
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "insert into tb_buyer value (null,?,?,?,?,null,?,?)";
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
			String sql = "delete from tb_buyer where B_id = ?";
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
	public boolean update(BuyerBean bean) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		int result = 0;
		
		int B_id = bean.getB_id();
		String username = bean.getB_username();
		String password = bean.getB_password();
		String phone = bean.getB_phone();
		String nickname = bean.getB_nickname();
		String createtime = bean.getB_createtime();
		String headportrait = bean.getB_headportrait();
		String intro = bean.getB_intro();
		
		username = username.equals("") ? getUsername(B_id) : username;
		password = password == null ? getPassword(B_id) : password;
		phone = phone.equals("") ? getPhone(B_id) : phone;
		nickname = nickname.equals("") ? getNickname(B_id) : nickname;
		headportrait = headportrait == null ? getHeadportrait(B_id) : headportrait;
		intro = intro.equals("") ? getIntro(B_id) : intro;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "update tb_buyer set B_username=?,B_password = ?,B_phone=?,"
					+ "B_nickname=?,B_createtime=?,B_headportrait=?,B_intro=? where B_id=?";
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, phone);
			pst.setString(4, nickname);
			pst.setString(5, createtime);
			pst.setString(6, headportrait);
			pst.setString(7, intro);
			pst.setInt(8, B_id);
			
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

	private String getIntro(int B_id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String result = "";  
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select B_intro from tb_buyer where B_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, B_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getString("B_intro");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst, rs);
		}
		return result;
	}
	
	private String getHeadportrait(int B_id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String result = "";
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select B_headportrait from tb_buyer where B_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, B_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getString("B_headportrait");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst, rs);
		}
		return result;
	}
	
	private String getNickname(int B_id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String result = "";
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select B_nickname from tb_buyer where B_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, B_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getString("B_nickname");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst, rs);
		}
		return result;
	}
	
	private String getPhone(int B_id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String result = "";
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select B_phone from tb_buyer where B_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, B_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getString("B_phone");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst, rs);
		}
		return result;
	}
	
	private String getUsername(int B_id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String result = "";
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select B_username from tb_buyer where B_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, B_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getString("B_username");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst, rs);
		}
		return result;
	}
	
	private String getPassword(int B_id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String pwd = "";
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select B_password from tb_buyer where B_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, B_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				pwd = rs.getString("B_password");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst, rs);
		}
		return pwd;
	}
	
	@Override
	public BuyerBean queryLogin(String username, String password) {
		BuyerBean bean = null;
		
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rSet= null;
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "select * from tb_buyer where B_username = ? and B_password = ?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			rSet = pStatement.executeQuery();
			
			while (rSet.next()) {
				int id = rSet.getInt("B_id");
				String name = rSet.getString("B_username");
				String pwd = rSet.getString("B_password");
				String phone = rSet.getString("B_phone");
				String nickname = rSet.getString("B_nickname");
				String time = rSet.getString("B_createtime");
				String head = rSet.getString("B_headportrait");
				String desc = rSet.getString("B_intro");
				bean = new BuyerBean(id, name, pwd, phone, nickname, time, head, desc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pStatement, rSet);
		}
		return bean;
	}

	@Override
	public int getIdByName(String B_username) {
		
		int B_id = 0;
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = JDBCPoolUtil.getConn();
			String  sql = "select B_id from tb_buyer where B_username = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, B_username);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				B_id = rs.getInt("B_id");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst, rs);
		}
		
		return B_id;
	
	}

	
}
