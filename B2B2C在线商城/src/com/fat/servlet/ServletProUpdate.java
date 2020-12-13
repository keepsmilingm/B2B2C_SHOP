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
import com.fat.pojo.ProductBean;

@WebServlet("/ServletProUpdate")
public class ServletProUpdate extends HttpServlet {
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
		ProductBean probean=null;
		JSONObject result = null;
		boolean flag = false;
		String P_id_str = req.getParameter("p_id");
		String P_inventory_str = req.getParameter("p_inventory");
		String P_name = req.getParameter("p_name");
		String P_price_str = req.getParameter("p_price");
		String P_desc = req.getParameter("p_desc");
		int P_id = 0;
		double P_price=0.0;
		int P_inventory=0;
		if (P_id_str != null)
			P_id = Integer.parseInt(P_id_str);
		if (P_price_str != null)
			P_price = Double.parseDouble(P_price_str);
		
		if (P_inventory_str != null)
			P_inventory = Integer.parseInt(P_inventory_str);
		
		probean = new ProductBean(P_id, P_name, P_desc,P_price,P_inventory);
		ProductDaoImpl Impl = new ProductDaoImpl();
		flag = Impl.update(probean);
		result = new JSONObject();
		
		if (flag) {
			result.put("state", true);
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求修改id为" + P_id + "的商品信息 ----- >请求成功");
		} else {
			result.put("state", false);
			System.out.print
			(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求修改id为" + P_id + "的商品信息 ----- >请求失败");
		}
		out.write(result.toJSONString());
	}

}
