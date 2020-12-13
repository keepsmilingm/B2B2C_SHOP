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
@WebServlet("/proByStore")
public class ServletProByStore extends HttpServlet{
	private static final long serialVersionUID = -2L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*"); //�����������
		resp.setContentType("text/xml;charset=utf-8");	//���������������
		PrintWriter out = resp.getWriter();
		String ip = req.getRemoteAddr();
		String Store_id = req.getParameter("St_id");
		
		int St_id =0;
		if (Store_id != null) {
			St_id = Integer.parseInt(Store_id);
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
		
		ProductDaoImpl pImpl = new ProductDaoImpl();
		List<ProductBean> list = pImpl.queryStore(St_id);
		out.write(JSON.toJSONString(list));
		
		if (list.size() > 0) {
			System.out.print(df.format(new Date()) + "����:");
			System.out.println(ip + "����" + St_id +"���̵����Ʒ ----- >����ɹ�,����������Ϣ");
		} else {
			System.out.print(df.format(new Date()) + "����:");
			System.out.println(ip + "����" + St_id +"���̵����Ʒ ----- >����ʧ��");
		}
		
	}

}
