package com.hx.mis.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.hx.mis.model.NationModel;
import com.hx.mis.model.NativeModel;
import com.util.Database;
import com.util.PropertiesParser;

public class NationPropertiesDao implements INationDao{
	static {
		Database.loadDatabaseConfig("/database.cfg.properties");
	}
	
	public NationPropertiesDao() {
	}
	
	@Override
	public List<NationModel> getNations() {
		List<NationModel> nationList = new ArrayList<>();
		
		// �õ����ļ���
		Set<String> nationKeys = PropertiesParser.keySet();
		Iterator<String> iterator = nationKeys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (key.startsWith("nation.")) {
				String id = key;
				String name = PropertiesParser.value(id);
				nationList.add(new NationModel(id.substring(7), name));
			}
		}
		// HashMap ��ŵ�ֵ��˳���������
		nationList.sort(new Comparator<NationModel>() {
			@Override
			public int compare(NationModel one, NationModel other) {
				return one.getId().compareTo(other.getId());
			}
		});
		
		return nationList;
	}

	@Override
	public List<NativeModel> getNatives() {
		List<NativeModel> nativeList = new ArrayList<>();
		
		// �õ����ļ���
		Set<String> nativeKeys = PropertiesParser.keySet();
		Iterator<String> iterator = nativeKeys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (key.startsWith("native.")) {
				String id = key;
				String name = PropertiesParser.value(id);
				nativeList.add(new NativeModel(id.substring(7), name));
			}
		}
		// HashMap ��ŵ�ֵ��˳���������
		nativeList.sort(new Comparator<NativeModel>() {
			@Override
			public int compare(NativeModel one, NativeModel other) {
				return one.getId().compareTo(other.getId());
			}
		});
		
		return nativeList;
	}
	
	
	

}
