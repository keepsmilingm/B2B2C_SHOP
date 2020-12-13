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
		
		resp.setHeader("Access-Control-Allow-Origin", "*"); //�����������
		resp.setContentType("text/xml;charset=utf-8");	//���������������
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
			System.out.print(df.format(new Date()) + "����:");
			System.out.println(ip + "����" + B_id + "�Ź��ﳵ����Ϣ ----- >����ɹ�");
		} else {
			System.out.print(df.format(new Date()) + "����:");
			System.out.println(ip + "����" + B_id + "�Ź��ﳵ����Ϣ ----- >����ʧ��");
		}
		out.write(JSON.toJSONString(list));
	}

}
