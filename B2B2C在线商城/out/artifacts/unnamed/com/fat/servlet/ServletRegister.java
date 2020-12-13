package com.fat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.alibaba.fastjson.JSONObject;
import com.fat.dao.impl.*;
import com.fat.pojo.AdminBean;
import com.fat.pojo.BuyerBean;
import com.fat.pojo.SellerBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletRegister extends HttpServlet{
	
	private static final long serialVersionUID = -4704210210175860746L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			resp.setHeader("Access-Control-Allow-Origin", "*");
			String username = req.getParameter("B_username");
			String password = req.getParameter("B_password");
			String phone = req.getParameter("B_phone");
			String nickname = req.getParameter("B_nickname");
			String intro = req.getParameter("B_intro");
			String type = req.getParameter("register_type");
			
			
			resp.setContentType("text/xml;charset=utf-8");
			PrintWriter out = resp.getWriter();
			
			String ip = req.getRemoteAddr();
			
			boolean flag = false;
			JSONObject resultObject = new JSONObject();
			
			if (type.equals("buyer")) {
				BuyerBean bean = new BuyerBean(null, username, password, phone, nickname, null, null, intro);
				BuyerDaoImpl bImpl = new BuyerDaoImpl();
				flag = bImpl.insert(bean);
				
				resultObject.put("state", flag);
				if(flag) {
					System.out.println(ip + "请求注册为买家----->注册成功");
					BuyerDaoImpl bImpl2 = new BuyerDaoImpl();
					int B_id = bImpl2.getIdByName(username);
					ShopCarDaoImpl scImpl = new ShopCarDaoImpl();
					scImpl.insert(B_id);
					
				} else {
					System.out.println(ip + "请求注册----->注册失败");
				}
			} else if (type.equals("seller")) {
				SellerBean bean = new SellerBean(null, username, password, phone, nickname, null, null, intro);
				SellerDaoImpl sImpl = new SellerDaoImpl();
				flag = sImpl.insert(bean);
				
				if (flag) {
					System.out.println(ip + "请求注册为商家----->注册成功");
				} else {
					System.out.println(ip + "请求注册为商家----->注册失败");
				}
				
			} else if (type.equals("administor")) {
				AdminBean bean = new AdminBean(null,username,password,nickname);
				AdminDaoImpl aImpl = new AdminDaoImpl();
				flag = aImpl.insert(bean);
			}
			out.write(resultObject.toJSONString());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
