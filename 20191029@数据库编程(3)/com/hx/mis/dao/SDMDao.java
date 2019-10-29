package com.hx.mis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hx.mis.model.SDMModel;
import com.util.Database;

public class SDMDao implements ISDMDao {
	private Database database;
	
	public SDMDao() {
		database = new Database();
	}

	@Override
	public List<SDMModel> getSdmList() {
		String sql = "SELECT id, name, status FROM sys_sch_dep_maj_info";
		ResultSet rs = database.executeQuery(sql);
		List<SDMModel> sdmList = new ArrayList<>();
		
		try {
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				boolean status = rs.getBoolean("status");
				sdmList.add(new SDMModel(id, name, status));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sdmList;
	}

	@Override
	public SDMModel getSdmById(String id) {
		String sql = "SELECT id, name, status FROM sys_sch_dep_maj_info WHERE id = '"	+ id + "'";
		ResultSet rs = database.executeQuery(sql);
		SDMModel sdm = null;
		
		try {
			if (rs.next()) {
				String name = rs.getString("name");
				boolean status = rs.getBoolean("status");
				sdm = new SDMModel(id, name, status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sdm;
	}

}
