package com.fat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.fat.dao.impl.BuyerDaoImpl;
import com.fat.dao.impl.SellerDaoImpl;
import com.fat.pojo.BuyerBean;
import com.fat.pojo.SellerBean;

@WebServlet("/ServletUpdate")
public class ServletUpdate extends HttpServlet {
	private static final long serialVersionUID = -6885473916850933770L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String ip = req.getRemoteAddr();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BuyerBean buyerbean=null;
		SellerBean sellerbean=null;
		JSONObject result = null;
		boolean flag = false;
		String B_id_str = req.getParameter("id");
		String identity=req.getParameter("identity");
		int B_id = 0;
		if (B_id_str != null)
			B_id = Integer.parseInt(B_id_str);
		if(identity.equals("buyer")) {
			String B_username = req.getParameter("username");
			String B_phone = req.getParameter("phone");
			String B_intro = req.getParameter("intro");
			String B_nickname = req.getParameter("nickName");
			String B_headportrait = req.getParameter("headportrait");
			buyerbean = new BuyerBean(B_id, B_username, null, B_phone, B_nickname, null, B_headportrait, B_intro);
			BuyerDaoImpl bImpl = new BuyerDaoImpl();
			flag = bImpl.update(buyerbean);
			result = new JSONObject();
		}
		if(identity.equals("seller")) {
			String S_username = req.getParameter("username");
			String S_phone = req.getParameter("phone");
			String S_intro = req.getParameter("intro");
			String S_nickname = req.getParameter("nickName");
			String S_headportrait = req.getParameter("headportrait");
			String St_name = req.getParameter("st_name");
			String St_desc = req.getParameter("st_desc");
	
			sellerbean = new SellerBean(B_id, S_username, null, S_phone, S_nickname, null, S_headportrait, S_intro,B_id,St_name,St_desc);
			SellerDaoImpl bImpl = new SellerDaoImpl();
			flag = bImpl.update(sellerbean);
			result = new JSONObject();
		}
		
		
	
		
		
		
		if (flag) {
			result.put("state", true);
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求修改id为" + B_id + "的用户信息信息 ----- >请求成功");
		} else {
			result.put("state", false);
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求修改id为" + B_id + "的用户信息信息 ----- >请求失败");
		}
		out.write(result.toJSONString());
	}

}
