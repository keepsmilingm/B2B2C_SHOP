package com.fat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.fat.dao.impl.BuyerDaoImpl;
import com.fat.pojo.BuyerBean;

public class ServletBuyer extends HttpServlet{
	
	private static final long serialVersionUID = 6330437177611053017L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String ip = req.getRemoteAddr();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String B_id_str = req.getParameter("id");
		String B_identity_str = req.getParameter("identity");
		int B_id = 0;
		if (B_id_str != null)
			B_id = Integer.parseInt(B_id_str);
		
		BuyerDaoImpl bImpl = new BuyerDaoImpl();
		BuyerBean bean = bImpl.queryById(B_id);
		
		if (bean != null) {
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求id为" + B_id + "的用户信息信息 ----- >请求成功");
		}else {
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求id为" + B_id + "的用户信息信息 ----- >请求失败");
		}
		
		out.write(JSON.toJSONString(bean));
	}
}
