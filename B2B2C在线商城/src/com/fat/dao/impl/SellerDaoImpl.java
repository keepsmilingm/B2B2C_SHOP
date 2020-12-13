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
			String sql = "select *from view_store_seller where S_username = ? and S_password = ?";
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
	
	public SellerBean queryById(int B_id) {
		SellerBean bean = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rSet = null;
		String sql = null;
		
		try {
			conn = JDBCPoolUtil.getConn();
		
			sql = "select * from view_store_seller where S_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, B_id);
			rSet = pst.executeQuery();
			while (rSet.next()) {
				String name = rSet.getString("S_username");
				String pwd = rSet.getString("S_password");
				String phone = rSet.getString("S_phone");
				String nickname = rSet.getString("S_nickname");
				String time = rSet.getString("S_createtime");
				String head = rSet.getString("S_headportrait");
				String desc = rSet.getString("S_intro");
				String st_name = rSet.getString("St_name");
				String st_desc = rSet.getString("St_desc");
				int st_no = rSet.getInt("St_no");
				bean = new SellerBean(B_id, name, pwd, phone, nickname, time, head, desc,st_no,st_name,st_desc);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst,rSet);
		}
		return bean;
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
				String st_name = rSet.getString("St_name");
				String st_desc = rSet.getString("St_desc");
				int st_no = rSet.getInt("St_no");
				SellerBean bean = new SellerBean(id, name, pwd, phone, nickname, time, head, desc,st_no,st_name,st_desc);
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
	
		
		int S_id = bean.getS_id();
		String username = bean.getS_username();
		String password = bean.getS_password();
		String phone = bean.getS_phone();
		String nickname = bean.getS_nickname();
		String createtime = bean.getS_createtime();
		String headportrait = bean.getS_headportrait();
		String intro = bean.getS_intro();
		int st_no = bean.getSt_no();
		username = username.equals("") ? getUsername(S_id) : username;
		password = password == null ? getPassword(S_id) : password;
		phone = phone.equals("") ? getPhone(S_id) : phone;
		nickname = nickname.equals("") ? getNickname(S_id) : nickname;
		headportrait = headportrait == null ? getHeadportrait(S_id) : headportrait;
		intro = intro.equals("") ? getIntro(S_id) : intro;
//		st_name = st_name.equals("") ? getSt_name(S_id) : intro;
//		st_desc = st_name.equals("") ? getSt_desc(S_id) : intro;
//		intro = intro.equals("") ? getIntro(S_id) : intro;
		
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
			pst.setInt(8, S_id);
			
			
			
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
			String sql = "select * from view_store_seller where S_username = ? and S_password = ?";
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
				String st_name = rSet.getString("St_name");
				String st_desc = rSet.getString("St_desc");
				int st_no = rSet.getInt("St_no");
				bean = new SellerBean(id, name, pwd, phone, nickname, time, head, desc,st_no,st_name,st_desc);			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pStatement, rSet);
		}
		
		return bean;
	}
	
	private String getIntro(int S_id) {
	    Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    String result = "";
	    try {
	        conn = JDBCPoolUtil.getConn();
	        String sql = "select S_intro from tb_seller where S_id = ?";
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, S_id);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            result = rs.getString("S_intro");
	        }
	    }catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	        JDBCPoolUtil.release(conn, pst, rs);
	    }
	    return result;
	}
	private String getSt_desc(int s_id) {
		Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    String result = "";
	    try {
	        conn = JDBCPoolUtil.getConn();
	        String sql = "select S_intro from view_store_seller where S_id = ?";
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, s_id);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            result = rs.getString("St_desc");
	        }
	    }catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	        JDBCPoolUtil.release(conn, pst, rs);
	    }
	    return result;
	}

	private String getSt_name(int s_id) {
		Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    String result = "";
	    try {
	        conn = JDBCPoolUtil.getConn();
	        String sql = "select S_intro from view_store_seller where S_id = ?";
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, s_id);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            result = rs.getString("St_name");
	        }
	    }catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	        JDBCPoolUtil.release(conn, pst, rs);
	    }
	    return result;
	}          

	private String getHeadportrait(int S_id) {
	    Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    String result = "";
	    try {
	        conn = JDBCPoolUtil.getConn();
	        String sql = "select S_headportrait from tb_seller where S_id = ?";
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, S_id);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            result = rs.getString("S_headportrait");
	        }
	    }catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	        JDBCPoolUtil.release(conn, pst, rs);
	    }
	    return result;
	}

	private String getNickname(int S_id) {
	    Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    String result = "";
	    try {
	        conn = JDBCPoolUtil.getConn();
	        String sql = "select S_nickname from tb_seller where S_id = ?";
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, S_id);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            result = rs.getString("S_nickname");
	        }
	    }catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	        JDBCPoolUtil.release(conn, pst, rs);
	    }
	    return result;
	}

	private String getPhone(int S_id) {
	    Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    String result = "";
	    try {
	        conn = JDBCPoolUtil.getConn();
	        String sql = "select S_phone from tb_seller where S_id = ?";
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, S_id);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            result = rs.getString("S_phone");
	        }
	    }catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	        JDBCPoolUtil.release(conn, pst, rs);
	    }
	    return result;
	}

	private String getUsername(int S_id) {
	    Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    String result = "";
	    try {
	        conn = JDBCPoolUtil.getConn();
	        String sql = "select S_username from tb_seller where S_id = ?";
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, S_id);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            result = rs.getString("S_username");
	        }
	    }catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	        JDBCPoolUtil.release(conn, pst, rs);
	    }
	    return result;
	}

	private String getPassword(int S_id) {
	    Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    String pwd = "";
	    try {
	        conn = JDBCPoolUtil.getConn();
	        String sql = "select S_password from tb_seller where S_id = ?";
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, S_id);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            pwd = rs.getString("S_password");
	        }
	    }catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	        JDBCPoolUtil.release(conn, pst, rs);
	    }
	    return pwd;
	}


	

}
