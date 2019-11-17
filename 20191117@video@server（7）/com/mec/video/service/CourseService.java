package com.mec.video.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mec.video.dao.CourseDao;
import com.mec.video.model.Course;

@Service
public class CourseService {
	@Autowired
	private CourseDao courseDao;
	
	public CourseService() {
	}

	public Course getCourseById(String id) {
		Course course = courseDao.getCourse(id);
		
		return course;
	}
	
}
