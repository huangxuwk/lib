package com.hx.mis.Main;

import com.hx.database.core.ClassTableFactory;
import com.hx.database.core.Database;

import com.hx.mis.view.StudentMisView;

public class MainView {

	public static void main(String[] args) {
		ClassTableFactory ctf = new ClassTableFactory();
		ctf.loadMapping("/classtable.mapping.xml");	
		Database.loadDatabaseConfig("/database.cfg.properties");
		
		new StudentMisView().showView();
	}

}
