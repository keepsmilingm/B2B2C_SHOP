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
import com.fat.dao.impl.OrdersDaoImpl;
import com.fat.dao.impl.ShopCarDaoImpl;
import com.fat.pojo.OrdersBean;

@WebServlet("/ServletOrder")
public class ServletOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setHeader("Access-Control-Allow-Origin", "*"); //�����������
		resp.setContentType("text/xml;charset=utf-8");	//���������������
		PrintWriter out = resp.getWriter();
		String ip = req.getRemoteAddr();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String B_id_str = req.getParameter("B_id");
		int B_id = 0;
		if (B_id_str != null)
			B_id = Integer.parseInt(B_id_str);
				
		
		String Pc_sum_str = req.getParameter("Pc_sum");
		double Pc_sum = 0;
		if (Pc_sum_str != null)
			Pc_sum = Double.parseDouble(Pc_sum_str);
		
		String address = req.getParameter("B_address");
		String name = req.getParameter("B_name");
		String phone = req.getParameter("B_phone");
		
		//���ɶ�������
		OrdersBean orderBean = new OrdersBean(1, name, phone, null, address, 0, Pc_sum, B_id);
		//1.���ɶ���
		OrdersDaoImpl oImpl = new OrdersDaoImpl();
		boolean result = oImpl.insert(orderBean);
		
		//ɾ�����ﳵ�������
		ShopCarDaoImpl scImpl = new ShopCarDaoImpl();
		scImpl.delete(B_id);
		
		JSONObject json = new JSONObject();
		boolean flag = false;
		if (result) {
			System.out.print(df.format(new Date()) + "����:");
			System.out.println(ip + "������ ----- >֧���ɹ�");
			flag = true;
		}else {
			System.out.print(df.format(new Date()) + "����:");
			System.out.println(ip + "������ ----- >֧��ʧ��");
		}
		json.put("state", flag);
		out.write(json.toJSONString());
	}

}
