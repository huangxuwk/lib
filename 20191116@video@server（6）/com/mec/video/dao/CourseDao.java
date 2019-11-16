package com.mec.video.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mec.video.model.Course;

@Component
@Transactional
public class CourseDao implements ISession {
	@Autowired
	private SessionFactory sessionFactory;
	
	public CourseDao() {
	}
	
	public Course getCourse(String id) {
		Course course = null;
		
		Session session = getSession(sessionFactory);
		course = session.get(Course.class, id);
		session.close();
		
		return course;
	}
	
}
