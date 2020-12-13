package com.fat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.fat.dao.impl.ProductDaoImpl;
import com.fat.pojo.ProductBean;
@WebServlet("/ProKeyword")
public class ServletProByKeyword extends HttpServlet{

	private static final long serialVersionUID = -724557982526409935L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String P_keyword = req.getParameter("P_keyword");
		System.out.println(P_keyword);
		
		

		resp.setHeader("Access-Control-Allow-Origin", "*");
		
		String ip = req.getRemoteAddr();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		ProductDaoImpl pImpl = new ProductDaoImpl();
		List<ProductBean> list = pImpl.queryByKeyword(P_keyword);
		
		if (list.size() > 0) {
			System.out.print(df.format(new Date()) + "����:");
			System.out.println(ip + "����ؼ���Ϊ" + P_keyword +"������Ʒ ----- >����ɹ�,����������Ϣ");
		} else {
			System.out.print(df.format(new Date()) + "����:");
			System.out.println(ip + "����" + P_keyword +"������Ʒ ----- >����ʧ��");
		}
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.write(JSON.toJSONString(list));
	}
}
