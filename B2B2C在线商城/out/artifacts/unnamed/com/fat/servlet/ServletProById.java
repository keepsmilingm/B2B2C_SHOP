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
			System.out.println("û�в���");
		}

		resp.setHeader("Access-Control-Allow-Origin", "*");
		
		String ip = req.getRemoteAddr();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		ProductDaoImpl pImpl = new ProductDaoImpl();
		ProductBean bean = pImpl.queryById(P_id);
		
		if (bean != null) {
			System.out.print(df.format(new Date()) + "����");
			System.out.print(ip + "������Ʒ��Ϣ -----> ");
			System.out.println("����ɹ�,��������idΪ" + bean.getP_id() + "����Ʒ��Ϣ");
		} else {
			System.out.print(df.format(new Date()) + "����");
			System.out.print(ip + "������Ʒ��Ϣ -----> ");
			System.out.println("��Ʒ��Ϣ������,����ʧ��");
		}
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.write(JSON.toJSONString(bean));
	}
}
