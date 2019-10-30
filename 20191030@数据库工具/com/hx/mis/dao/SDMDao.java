package com.hx.mis.dao;

import java.util.List;

import com.hx.database.core.Database;
import com.hx.mis.model.SDMModel;

public class SDMDao implements ISDMDao {
	private Database database;
	
	public SDMDao() {
		database = new Database();
	}

	@Override
	public List<SDMModel> getSdmList() {
		return database.list(SDMModel.class);
	}

	@Override
	public SDMModel getSdmById(String id) {
		return database.get(SDMModel.class, id);
	}

}
