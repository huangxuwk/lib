package com.hx.database.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.PropertiesParser;

public class Database {
	private static Connection connection;
	
	public Database() {
	}

	// ͨ��·���� properties �ļ�
	public static void loadDatabaseConfig(String path) {
		PropertiesParser.loadProperties(path);
	}

	//  ͨ������ģʽ��ȡ connection
	private static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName(PropertiesParser.value("driver"));
				connection = DriverManager.getConnection(
						PropertiesParser.value("url"),
						PropertiesParser.value("user"),
						PropertiesParser.value("password"));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> list(Class<?> klass) {
		ClassTable ct = ClassTableFactory.getClassTable(klass);
		if (ct == null) {
			return null;
		}
		String sql = "SELECT " + ct.getColumnString() + " FROM " + ct.getTable();
		List<T> result = new ArrayList<>();
		try {
			PreparedStatement state = getConnection().prepareStatement(sql);
			ResultSet rs = state.executeQuery();
			
			while (rs.next()) {
				// ��������Ķ����൱��һ����¼
				Object object = klass.newInstance();
				// ��һ����¼��ֵ��һ��model��Ӧ�Ķ���
				ct.setFieldFromResultSet(rs, object);
				System.out.println("---------");
				result.add((T) object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(Class<?> klass, String id) {
		ClassTable ct = ClassTableFactory.getClassTable(klass);
		if (ct == null) {
			return null;
		}
		String sql = "SELECT " + ct.getColumnString() + " FROM " + ct.getTable()
				+ " WHERE " + ct.getIdString() + "=?";
		Connection connection = getConnection();
		try {
			PreparedStatement state = connection.prepareStatement(sql);
			state.setObject(1, id);
			ResultSet rs = state.executeQuery();
			if (rs.next()) {
				Object object = klass.newInstance();
				ct.setFieldFromResultSet(rs, object);
				return (T) object;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}


