package com.lgw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.lgw.bean.Bar;

public class BarDao {

	/**
	 * @author wen
	 * 
	 * 鏌ヨ鎵�鏈夋暟鎹�
	 * @return 瀛樻斁鎵�鏈夋暟鎹殑绾挎�ц〃
	 */
	public String query() {
		ArrayList<Bar> barArr = new ArrayList<Bar>();
		try {
			//JDBC鏂瑰紡杩炴帴MySQL鏁版嵁搴�
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/echartsdb?characterEncoding=utf8", "root", "");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bar");
			ResultSet rs = stmt.executeQuery();
			
			
			
			//灏唗est琛ㄤ腑鐨勬暟鎹瓨鍌ㄥ埌绾挎�ц〃涓�
			while(rs.next()) {
				Bar bar = new Bar();
				bar.setName(rs.getString("name"));
				bar.setNum(rs.getInt("num"));
								barArr.add(bar);
			}
			//鍏抽棴杩炴帴
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Gson gson =new Gson();
		String str=gson.toJson(barArr);
		
		return str;
	}
	
	public void update(String name,Integer num) {
		try {
			//JDBC鏂瑰紡杩炴帴MySQL鏁版嵁搴�
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/echartsdb?characterEncoding=utf8", "root", "");
			PreparedStatement stmt = conn.prepareStatement("update bar set num='"+num+"' where name='"+name+"'");
			stmt.executeUpdate();
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
