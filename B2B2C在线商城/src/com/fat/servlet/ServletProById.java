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
import com.fat.dao.impl.ProductDaoImpl;
import com.fat.pojo.ProductBean;

public class ServletProById extends HttpServlet{

	private static final long serialVersionUID = -724557982526409935L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String P_id_str = req.getParameter("P_id");
		int P_id = 0;
		if (P_id_str != null) {
			P_id = Integer.parseInt(P_id_str);
		} else {
			System.out.println("没有参数");
		}

		resp.setHeader("Access-Control-Allow-Origin", "*");
		
		String ip = req.getRemoteAddr();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		ProductDaoImpl pImpl = new ProductDaoImpl();
		ProductBean bean = pImpl.queryById(P_id);
		
		if (bean != null) {
			System.out.print(df.format(new Date()) + "主机");
			System.out.print(ip + "请求商品信息 -----> ");
			System.out.println("请求成功,即将发送id为" + bean.getP_id() + "的商品信息");
		} else {
			System.out.print(df.format(new Date()) + "主机");
			System.out.print(ip + "请求商品信息 -----> ");
			System.out.println("商品信息不存在,请求失败");
		}
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.write(JSON.toJSONString(bean));
	}
}
