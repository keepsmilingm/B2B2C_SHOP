package com.fat.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCPoolUtil {
	public static Connection getConn () {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			ctx = (Context) ctx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("DBPool");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void release (Connection conn,PreparedStatement pst) {
		try {
			conn.close();
			closePst(pst);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void release (Connection conn,PreparedStatement pst,ResultSet rs) {
		try {
			conn.close();
			closeRs(rs);
			closePst(pst);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void closeRs (ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs = null;
		}
	}
	
	private static void closePst (PreparedStatement pst) {
		try {
			if (pst != null)
				pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pst = null;
		}
	}
}
