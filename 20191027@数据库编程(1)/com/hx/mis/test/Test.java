package com.hx.mis.test;

import com.hx.mis.dao.NationDao;
import com.mec.util.PropertiesParser;

public class Test {

	public static void main(String[] args) {
		PropertiesParser.loadProperties("/interfaces.cfg.properties");
		NationDao dao = new NationDao();
		dao.getNationList();
	}

}
