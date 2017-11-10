package com.lgw.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lgw.bean.Bar;
import com.lgw.dao.BarDao;

import net.sf.json.JSONArray;

public class JsoService extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
//		resp.setContentType("application/x-json;charset=utf-8");
		
//		String obj=req.getParameter("user");
//		
//		System.out.println(obj);
		
//		Gson gson=new Gson();
//		Bar bar=gson.fromJson(obj, Bar.class);
//		System.out.println(bar.getName());
		
		String name=req.getParameter("name");
		String num=req.getParameter("num");
		//		System.out.println(name+num+"!!!!!");
//		
		BarDao bDao=new BarDao();
		bDao.update(name, Integer.parseInt(num));
		
		resp.sendRedirect("index.jsp");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
