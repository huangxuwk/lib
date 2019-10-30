package com.hx.mis.dao;

import java.util.List;

import com.hx.mis.model.SDMModel;

public interface ISDMDao {
	List<SDMModel> getSdmList();
	SDMModel getSdmById(String id);
}
