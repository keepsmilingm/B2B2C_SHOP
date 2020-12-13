package com.fat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.fat.dao.impl.ProductDaoImpl;
import com.fat.pojo.ProductBean;

public class ServletProduct extends HttpServlet{

	private static final long serialVersionUID = -8213710710094925402L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*"); //解决跨域问题
		resp.setContentType("text/xml;charset=utf-8");	//解决中文乱码问题
		PrintWriter out = resp.getWriter();
		String ip = req.getRemoteAddr();
		String goods_type = req.getParameter("type");
		
		int type = 0;
		if (goods_type != null) {
			type = Integer.parseInt(goods_type);
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
		
		ProductDaoImpl pImpl = new ProductDaoImpl();
		List<ProductBean> list = pImpl.queryType(type);
		out.write(JSON.toJSONString(list));
		
		if (list.size() > 0) {
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求" + type +"类型商品 ----- >请求成功,发送所有信息");
		} else {
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求" + type +"类型商品 ----- >请求失败");
		}
		
	}
}
