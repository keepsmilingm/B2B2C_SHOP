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
import com.fat.dao.impl.ProductCarDaoImpl;
import com.fat.pojo.ProductCarBackBean;

/**
 * Servlet implementation class ServletShopCar
 */
@WebServlet("/ServletShopCar")
public class ServletShopCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setHeader("Access-Control-Allow-Origin", "*"); //解决跨域问题
		resp.setContentType("text/xml;charset=utf-8");	//解决中文乱码问题
		PrintWriter out = resp.getWriter();
		String ip = req.getRemoteAddr();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String B_id_str = req.getParameter("B_id");
		int B_id = 0;
		if (B_id_str != null)
			B_id = Integer.parseInt(B_id_str);
		
		ProductCarDaoImpl pCarDaoImpl = new ProductCarDaoImpl();
		List<ProductCarBackBean> list = pCarDaoImpl.getProduct(B_id);
		
		if (list.size() >0) {
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求" + B_id + "号购物车的信息 ----- >请求成功");
		} else {
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求" + B_id + "号购物车的信息 ----- >请求失败");
		}
		out.write(JSON.toJSONString(list));
	}

}
