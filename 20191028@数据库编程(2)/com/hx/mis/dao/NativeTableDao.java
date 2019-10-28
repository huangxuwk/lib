package com.hx.mis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hx.mis.model.NativeModel;
import com.util.Database;

public class NativeTableDao implements INativeDao {
	private Database database;
	
	// 单例模式，只读取一次文件，只生成一个 Map
	static {
		Database.loadDatabaseConfig("/database.cfg.properties");
	}
	
	public NativeTableDao() {
		database = new Database();
	}
	
	@Override
	public List<NativeModel> getNatives() {
		/*
		 * 列表里每一个节点就是每一条记录，将对象存入
		 */
		List<NativeModel> nativeList = new ArrayList<>();
		
		String sql = "SELECT id, name FROM sys_native_cnst";
		ResultSet rs = database.executeQuery(sql);
		try {
			while (rs.next()) {
			/*
			 * ResultSet 是结果集，即，表；一个表在打开后，其“指针（游标、下标）”
			 * 指向第一条记录的前面！ 必须通过 next() 方法， 将其移动到第一条记录
			 * 若表为空，即，没有任何记录，next() 将把游标移动到“记录末尾”。
			 * 所谓记录末尾指：最后一条记录的后面。
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

