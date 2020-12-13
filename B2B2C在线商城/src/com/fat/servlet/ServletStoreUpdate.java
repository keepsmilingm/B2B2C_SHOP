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
import com.fat.dao.impl.StoreDaoImpl;
import com.fat.pojo.StoreBean;

@WebServlet("/ServletStoreUpdate")
public class ServletStoreUpdate extends HttpServlet {
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
		StoreBean storebean=null;
		JSONObject result = null;
		boolean flag = false;
		String St_id_str = req.getParameter("id");
		String St_name = req.getParameter("st_name");
		String St_desc = req.getParameter("st_desc");
		int St_id = 0;
		if (St_id_str != null)
			St_id = Integer.parseInt(St_id_str);
		
		storebean = new StoreBean(St_id, St_name, St_desc);
		StoreDaoImpl bImpl = new StoreDaoImpl();
		flag = bImpl.update(storebean);
		result = new JSONObject();
		
		if (flag) {
			result.put("state", true);
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求修改id为" + St_id + "的商铺信息 ----- >请求成功");
		} else {
			result.put("state", false);
			System.out.print
			(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求修改id为" + St_id + "的商铺信息 ----- >请求失败");
		}
		out.write(result.toJSONString());
	}

}
