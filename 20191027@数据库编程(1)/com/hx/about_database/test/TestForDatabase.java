package com.hx.about_database.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestForDatabase {
	
		public static void main(String[] args) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// 数据库名字后面不能加空格！！！！
				// useSSL=false  防止出现警告
				String url = "jdbc:mysql://localhost:3306/mec_javase_201905?useUnicode=true&characterEncoding=utf-8&useSSL=false";
//				String url = "jdbc:mysql://127.0.0.1:3306/mec_javase_201905";
				String user = "root";
				String password = "199866";
				Connection connection = DriverManager.getConnection(url, user, password);
				String sql = "SELECT sys_student_info.id, stu_id, sys_student_info.name AS name,"
						+ " sys_native_cnst.name AS nativer, sys_nation_cnst.name AS nation, s_d_m, status"
						+ " FROM sys_student_info, sys_native_cnst, sys_nation_cnst"
						+ " WHERE sys_native_cnst.id = sys_student_info.native"
						+ " AND sys_nation_cnst.id = sys_student_info.nation";
//				String sql = "SELECT sys_student_info.id, stu_id, sys_student_info.name AS name,"
//						+ " sys_native_cnst.name AS nativer, sys_nation_cnst.name AS nation,  s_d_m, status"
//						+ " FROM sys_student_info, sys_native_cnst, sys_nation_cnst"
//						+ "WHERE sys_native_cnst.id = sys_student_info.native"
//						+ "AND sys_nation_cnst.id = sys_student_info.nation";
				PreparedStatement state = connection.prepareStatement(sql);
				ResultSet rs = state.executeQuery();
				
				while (rs.next()) {
					String id = rs.getString("id");
					String stuId = rs.getString("stu_id");
					String name = rs.getString("name");
					String nativer = rs.getString("nativer");
					String nation = rs.getString("nation");
					System.out.println(id + ":" + stuId + " " + name + " " + nativer + " " + nation);
				}
				
//				String peopleId = "61232219980306091X";
//				String stuId = "03172344";
//				String name = "余林涛";
//				String nativer = "02";
//				String nation = "610";
//				String schoolDepartmentMajor = "010101";
//				
//				sql = "INSERT INTO sys_student_info (id , stu_id, name, native, nation, s_d_m, status)"
//							+ "VALUES(?, ?, ?, ?, ? ,?, 1)";
//				state = connection.prepareStatement(sql);
//				state.setString(1, peopleId);
//				state.setString(2, stuId);
//				state.setString(3, name);
//				state.setString(4, nativer);
//				state.setString(5, nation);
//				state.setString(6, schoolDepartmentMajor);
//				state.executeUpdate();
				
//				sql = "INSERT INTO sys_student_info (id, stu_id, name, native, nation, s_d_m, status)\r\n" +
//				" VALUES("peopleId, stuId, name, nativer, nation, schoolDepartmentMajor")";
//				state = connection.prepareStatement(sql);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
}
