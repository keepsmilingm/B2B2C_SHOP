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

import com.alibaba.fastjson.JSON;
import com.fat.dao.impl.SellerDaoImpl;
import com.fat.pojo.SellerBean;

@WebServlet("/ServletSeller")
public class ServletSeller extends HttpServlet{
	
	private static final long serialVersionUID = 6330431053017L;

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
		
		String S_id_str = req.getParameter("id");
		String S_identity_str = req.getParameter("identity");
		int S_id = 0;
		if (S_id_str != null)
			S_id = Integer.parseInt(S_id_str);
		
		SellerDaoImpl bImpl = new SellerDaoImpl();
		SellerBean bean = bImpl.queryById(S_id);
		
		if (bean != null) {
			System.out.print(df.format(new Date()) + "����:");
			System.out.println(ip + "����idΪ" + S_id + "���û���Ϣ��Ϣ ----- >����ɹ�");
		}else {
			System.out.print(df.format(new Date()) + "����:");
			System.out.println(ip + "����idΪ" + S_id + "���û���Ϣ��Ϣ ----- >����ʧ��");
		}
		
		out.write(JSON.toJSONString(bean));
	}
}
