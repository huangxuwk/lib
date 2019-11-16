package com.mec.video.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.video.dao.StudentDao;
import com.mec.video.model.StudentBaseInfo;
import com.mec.video.model.StudentVideoModel;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentDao;

	public StudentService() {
	}

	public StudentVideoModel getStudentById(String id, String password) {
		StudentVideoModel stu = studentDao.getStudentById(id);
		
		if (stu == null) {
			return null;
		}
		if (!stu.getPassword().equals(password)) {
			return null;
		}
		
		return stu;
	}
	
	public StudentBaseInfo getStudentBaseInfo(String peopleId) {
		return studentDao.getStudentBaseInfoByPeopleId(peopleId);
	}
	
}
