package com.mec.video.dao;

import com.mec.video.model.StudentInfo;

public class StudentDao {
	
	public StudentDao() {
	}

	public StudentInfo getStudentById(String id) {
		StudentInfo stu = new StudentInfo();
		
		stu.setId(id);
		stu.setName("��ʺ");
		stu.setPeopleId("612122200401102143");
		stu.setPassword(String.valueOf("240616".hashCode()));
		stu.setStatus("0");
		// TODO ���ݿ����
		
		return stu;
	}
	
}
