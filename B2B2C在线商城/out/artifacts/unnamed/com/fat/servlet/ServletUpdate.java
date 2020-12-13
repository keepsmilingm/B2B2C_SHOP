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
import com.fat.dao.impl.BuyerDaoImpl;
import com.fat.pojo.BuyerBean;

@WebServlet("/ServletUpdate")
public class ServletUpdate extends HttpServlet {
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
		
		String B_id_str = req.getParameter("B_id");
		int B_id = 0;
		if (B_id_str != null)
			B_id = Integer.parseInt(B_id_str);
		String B_username = req.getParameter("B_username");
		String B_phone = req.getParameter("B_phone");
		String B_intro = req.getParameter("B_intro");
		String B_nickname = req.getParameter("B_nickName");
		String B_headportrait = req.getParameter("B_headportrait");
		BuyerBean bean = new BuyerBean(B_id, B_username, null, B_phone, B_nickname, null, B_headportrait, B_intro);

//		System.out.println("name:" + B_username);
//		System.out.println("phone" + B_phone);
//		System.out.println("intro" + B_intro);
//		System.out.println("nickname" + B_nickname);
//		System.out.println("headportrait" + B_headportrait);
		
		BuyerDaoImpl bImpl = new BuyerDaoImpl();
		boolean flag = bImpl.update(bean);
		JSONObject result = new JSONObject();
		
		if (flag) {
			result.put("state", true);
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求修改id为" + B_id + "的用户信息信息 ----- >请求成功");
		} else {
			result.put("state", false);
			System.out.print(df.format(new Date()) + "主机:");
			System.out.println(ip + "请求修改id为" + B_id + "的用户信息信息 ----- >请求失败");
		}
		out.write(result.toJSONString());
	}

}
