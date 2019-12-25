package com.mec.video.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.video.dao.StudentDao;
import com.mec.video.model.StudentModel;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentDao;
	private Map<String, StudentModel> studentPool;
	
	public StudentService() {
		studentPool = new HashMap<String, StudentModel>();
	}

	private StudentModel getStudentById(String id) {
		StudentModel student = studentPool.get(id);
		if (student == null) {
			StudentModel stu = studentDao.getStudentById(id);
			if (stu != null) {
				studentPool.put(stu.getId(), stu);
				
				return stu;
			}
		}
		
		return student;
	}
	
	public String studentLogin(String id, String password) {
		StudentModel student = getStudentById(id);
		if (student == null) {
			return "ERROR";
		}
		if (!student.getPassword().equals(password)) {
			return "ERROR";
		}
		
		return "SUCCESS";
	}
	
}
