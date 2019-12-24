package com.mec.video.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.video.dao.StudentDao;
import com.mec.video.model.StudentModel;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentDao;
	
	public StudentService() {
	}

	public String studentLogin(String id, String password) {
		StudentModel student = studentDao.getStudentById(id);
		if (student == null) {
			return "ERROR";
		}
		if (!student.getPassword().equals(
				String.valueOf(password.hashCode()))) {
			return "ERROR";
		}
		
		return "SUCCESS";
	}
	
}
