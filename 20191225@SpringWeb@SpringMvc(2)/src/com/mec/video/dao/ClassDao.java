package com.mec.video.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mec.video.model.ClassModel;

@Component
public class ClassDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public ClassDao() {
	}
	
	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}

	public Map<String, ClassModel> getClassList() {
		Session session = getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<ClassModel> classList = session
			.createQuery("FROM ClassModel").getResultList();
		Map<String, ClassModel> classMap = new HashMap<>();
		for (ClassModel clazz : classList) {
			String id = clazz.getId();
			classMap.put(id, clazz);
		}
		session.close();
		
		return classMap;
	}
	
}
