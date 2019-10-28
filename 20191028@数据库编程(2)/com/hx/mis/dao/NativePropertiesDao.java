package com.hx.mis.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.hx.mis.model.NativeModel;
import com.util.PropertiesParser;

public class NativePropertiesDao implements INativeDao {
	static {
		PropertiesParser.loadProperties("/database.cfg.properties");
	}
	
	public NativePropertiesDao() {
	}
	
	@Override
	public List<NativeModel> getNatives() {
		List<NativeModel> nativeList = new ArrayList<>();
		
		Set<String> nativeKeys = PropertiesParser.keySet();
		Iterator<String> iterator = nativeKeys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			// 读取指定的键值对
			if (key.startsWith("native.")) {
				String id = key;
				String name = PropertiesParser.value(id);
				nativeList.add(new NativeModel(id.substring(7), name));
			}
		}
		nativeList.sort(new Comparator<NativeModel>() {
			@Override
			public int compare(NativeModel one, NativeModel other) {
				return one.getId().compareTo(other.getId());
			}
		});
				
		return nativeList;
	}

}
