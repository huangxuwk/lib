package com.hx.mis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hx.mis.model.NativeModel;
import com.util.Database;

public class NativeTableDao implements INativeDao {
	private Database database;
	
	// ����ģʽ��ֻ��ȡһ���ļ���ֻ����һ�� Map
	static {
		Database.loadDatabaseConfig("/database.cfg.properties");
	}
	
	public NativeTableDao() {
		database = new Database();
	}
	
	@Override
	public List<NativeModel> getNatives() {
		/*
		 * �б���ÿһ���ڵ����ÿһ����¼�����������
		 */
		List<NativeModel> nativeList = new ArrayList<>();
		
		String sql = "SELECT id, name FROM sys_native_cnst";
		ResultSet rs = database.executeQuery(sql);
		try {
			while (rs.next()) {
			/*
			 * ResultSet �ǽ������������һ�����ڴ򿪺��䡰ָ�루�αꡢ�±꣩��
			 * ָ���һ����¼��ǰ�棡 ����ͨ�� next() ������ �����ƶ�����һ����¼
			 * ����Ϊ�գ�����û���κμ�¼��next() �����α��ƶ�������¼ĩβ����
			 * ��ν��¼ĩβָ�����һ����¼�ĺ��档
			 */
				String id = rs.getString("id");
				String name = rs.getString("name");
				nativeList.add(new NativeModel(id, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nativeList;
	}

}

