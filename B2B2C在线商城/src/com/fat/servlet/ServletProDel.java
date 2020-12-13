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
import com.fat.dao.impl.ProductDaoImpl;

@WebServlet("/ServletProDel")
public class ServletProDel extends HttpServlet {
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
		JSONObject result = null;
		boolean flag = false;
		String P_id_str = req.getParameter("p_id");
		int P_id = 0;
		if (P_id_str != null)
			P_id = Integer.parseInt(P_id_str);
		ProductDaoImpl Impl = new ProductDaoImpl();
		flag = Impl.delete(P_id);
		result = new JSONObject();
		
		if (flag) {
			result.put("state", true);
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求删除id为" + P_id + "的商品信息 ----- >请求成功");
		} else {
			result.put("state", false);
			System.out.print
			(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求删除id为" + P_id + "的商品信息 ----- >请求失败");
		}
		out.write(result.toJSONString());
	}

}
