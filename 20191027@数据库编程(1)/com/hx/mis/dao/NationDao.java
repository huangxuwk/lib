package com.hx.mis.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.hx.mis.medol.NationModel;
import com.mec.util.PropertiesParser;

public class NationDao {
	
	public NationDao() {
	}

	public List<NationModel> getNationList() {
		List<NationModel> result = new ArrayList<>();
		
		Set<String> keySet = PropertiesParser.keySet();
		Iterator<String> keys = keySet.iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			System.out.println(key);
		}
		
		return result;
	}
	
}
