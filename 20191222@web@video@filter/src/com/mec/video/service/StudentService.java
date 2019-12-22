package com.mec.video.service;

import com.mec.video.dao.StudentDao;
import com.mec.video.model.StudentInfo;

public class StudentService {
	private StudentDao studentDao;
	
	public StudentService() {
	}
	
	public StudentInfo getStudent(String id, String password) {
		studentDao = new StudentDao();
		StudentInfo student = studentDao.getStudentById(id);
		
		if (student == null) {
			return null;
		}
		if (!student.getPassword().equals(password)) {
			return null;
		}
		
		return student;
	}
	
}
