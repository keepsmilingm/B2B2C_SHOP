package com.fat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.fat.dao.impl.BuyerDaoImpl;
import com.fat.dao.impl.SellerDaoImpl;
import com.fat.pojo.BuyerBean;
import com.fat.pojo.SellerBean;

public class ServletLogin extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6541151983387417702L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			resp.setHeader("Access-Control-Allow-Origin", "*");
			
			String identity = req.getParameter("identity");
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String ip = req.getRemoteAddr();
			
			resp.setContentType("text/xml;charset=utf-8");
			PrintWriter out = resp.getWriter();
			
			if (identity.equals("buyer")) {
				BuyerDaoImpl bImpl = new BuyerDaoImpl();
				BuyerBean bean = bImpl.queryLogin(username, password);
				if (bean != null) {
					System.out.println(ip + "请求登录----->查询成功.即将发送id为" + bean.getB_id() + "的用户信息");
				} else {
					System.out.println(ip + "请求登录----->未找到数据");
				}
				out.write(JSON.toJSONString(bean));
			}
			if (identity.equals("seller")) {	
				SellerDaoImpl sImpl = new SellerDaoImpl();
				SellerBean bean = sImpl.queryLogin(username, password);
				if (bean != null) {
					System.out.println(ip + "请求登录----->查询成功.即将发送id为" + bean.getS_id() + "的商家信息");
				} else {
					System.out.println(ip + "请求登录----->未找到数据");
				}
				out.write(JSON.toJSONString(bean));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
