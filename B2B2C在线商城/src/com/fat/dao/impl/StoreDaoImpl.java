package com.fat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fat.dao.StoreDao;
import com.fat.pojo.StoreBean;
import com.fat.util.JDBCPoolUtil;

public class StoreDaoImpl implements StoreDao{

	@Override
	public StoreBean queryById(int st_id) {
		boolean flag=false;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		StoreBean bean = null;
		
		try {
			conn=JDBCPoolUtil.getConn();
			String sql="select *from tb_store where St_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, st_id);
			rs=pst.executeQuery();
			if(rs.next()) {
				
				String st_name=rs.getString("St_name");
				String st_desc=rs.getString("St_desc");
				bean=new StoreBean(st_id, st_name, st_desc);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst,rs);
		}
		return bean;
	}

	@Override
	public boolean insert(StoreBean bean) {
		String st_name=bean.getSt_name();
		String st_desc=bean.getSt_desc();
		Connection conn=null;
		PreparedStatement pst=null;
		int rs=0;
		
		
		try {
			conn=JDBCPoolUtil.getConn();
			String sql="insert into tb_store value(null,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, st_name);
			pst.setString(2, st_desc);
			rs=pst.executeUpdate();
			
		}catch(SQLException e){
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
	public boolean delete(int st_id) {

		Connection conn=null;
		PreparedStatement pst=null;
		int rs=0;
		
		
		try {
			conn=JDBCPoolUtil.getConn();
			String sql="delete from tb_store where St_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, st_id);
			rs=pst.executeUpdate();
			
		}catch(SQLException e){
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
	public boolean update(StoreBean bean) {
		String st_name=bean.getSt_name();
		String st_desc=bean.getSt_desc();
		Connection conn=null;
		PreparedStatement pst=null;
		int rs=0;
		
		
		try {
			conn=JDBCPoolUtil.getConn();
			String sql="update tb_store set St_name=?,St_desc=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, st_name);
			pst.setString(2, st_desc);
			rs=pst.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JDBCPoolUtil.release(conn, pst);
		}
		if(rs > 0) 
			return true;
		else
			return false;
	}

}
