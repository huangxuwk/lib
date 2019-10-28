package com.hx.mis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hx.mis.model.NationModel;
import com.util.Database;

public class NationTableDao implements INationDao {
	private Database database;
	
	static {
		Database.loadDatabaseConfig("/database.cfg.properties");
	}
	
	public NationTableDao() {
		database = new Database();
	}
	
	@Override
	public List<NationModel> getNations() {
		List<NationModel> nationList = new ArrayList<>();
		
		String sql = "SELECT id, name FROM sys_nation_cnst";
		ResultSet rs = database.executeQuery(sql);
		try {
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				nationList.add(new NationModel(id, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nationList;
	}
}


