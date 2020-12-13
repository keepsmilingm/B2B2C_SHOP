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
import com.fat.dao.impl.ProductCarDaoImpl;

/**
 * Servlet implementation class ServletAddOrders
 */
@WebServlet("/ServletAddOrders")
public class ServletAddOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setHeader("Access-Control-Allow-Origin", "*"); //解决跨域问题
		resp.setContentType("text/xml;charset=utf-8");	//解决中文乱码问题
		PrintWriter out = resp.getWriter();
		String ip = req.getRemoteAddr();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		JSONObject result = new JSONObject();
		
		int P_id = 0;
		int B_id = 0;
		
		String P_id_str = req.getParameter("P_id");
		String B_id_str = req.getParameter("B_id");
		
		if (P_id_str != null) {
			P_id = Integer.parseInt(P_id_str);
		}
		if (B_id_str != null) {
			B_id = Integer.parseInt(B_id_str);
		}
		
		ProductCarDaoImpl pCarDaoImpl = new ProductCarDaoImpl();
		boolean flag = pCarDaoImpl.insert(B_id, P_id);
		
		if (flag) {
			result.put("state", true);
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "往" + B_id + "号购物车里添加商品 ----- >添加成功");
		} else {
			result.put("state", false);
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "往" + B_id + "号购物车里添加商品 ----- >添加失败");
		}
		out.write(result.toJSONString());
	}

}
