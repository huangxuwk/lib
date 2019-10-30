package com.hx.mis.dao;

import java.util.List;

import com.hx.database.core.Database;
import com.hx.mis.model.NationModel;
import com.hx.mis.model.NativeModel;

public class NationTableDao implements INationDao {
	private Database database;
	
	public NationTableDao() {
		database = new Database();
	}
	
	@Override
	public List<NationModel> getNations() {
		return database.list(NationModel.class);
	}

	@Override
	public List<NativeModel> getNatives() {
		return database.list(NativeModel.class);
	}
}


