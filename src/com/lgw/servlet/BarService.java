package com.lgw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lgw.bean.Bar;
import com.lgw.dao.BarDao;

import net.sf.json.JSONArray;

public class BarService extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		//����DAO
		BarDao barDao = new BarDao();
		//�����ݿ���ȡ����
		String barArr = barDao.query();
		//���÷�������Ӧʱ��JSP��ʾ�㴫�����ݵı����ʽ
		resp.setContentType("text/html; charset=utf-8");
		//ArrayList����ת��ΪJSON����
		JSONArray json = JSONArray.fromObject(barArr);
		//����̨��ʾJSON
		System.out.println(json.toString());
		//���ص�JSP
		PrintWriter writer = resp.getWriter();
		writer.println(json);
		writer.flush();
		//�ر������
		writer.close();
	}
}
