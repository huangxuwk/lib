package com.hx.mis.Test;

import com.hx.database.core.ClassTableFactory;
import com.hx.database.core.Database;
import com.hx.mis.model.StudentModel;

public class Test {
	
	public static void main(String[] args) {
		ClassTableFactory ctf = new ClassTableFactory();
		ctf.loadMapping("/classtable.mapping.xml");		
		Database.loadDatabaseConfig("/database.cfg.properties");
		
		Database database = new Database();
		StudentModel student = database.get(StudentModel.class, "01181003");
		System.out.println(student);
	}
}
