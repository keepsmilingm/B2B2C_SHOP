package com.fat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fat.dao.AdminDao;
import com.fat.pojo.AdminBean;
import com.fat.util.JDBCPoolUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public boolean insert(AdminBean bean) {

		boolean flag = false;
		
		Connection conn = null;
		PreparedStatement pst = null;
		
		String username = bean.getAd_username();
		String password = bean.getAd_password();
		String nickname = bean.getAd_nickname();
		
		try {
			conn = JDBCPoolUtil.getConn();
			String sql = "insert into tb_admin value(null,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, nickname);
			
			int result = pst.executeUpdate();
			
			if (result  > 0)
				flag = true;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		
		return flag;
		
	}
	
}
