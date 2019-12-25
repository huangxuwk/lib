package com.mec.video.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mec.video.model.StudentModel;
import com.mec.video.model.StudentPhotoModel;

@Component
public class StudentDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public StudentDao() {
	}
	
	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}

	public StudentPhotoModel getStudentPhoto(String peopleId) {
		StudentPhotoModel studentPhoto = null;
		
		Session session = getCurrentSession();
		@SuppressWarnings("unchecked")
		List<StudentPhotoModel> photoList = session.createQuery(
				"from StudentPhotoModel where peopleId=" + peopleId)
				.getResultList();
		if (photoList.isEmpty()) {
			return null;
		}
		studentPhoto = photoList.get(0);
		session.close();
		
		return studentPhoto;
	}
	
	public StudentModel getStudentById(String id) {
		System.out.println("getStudentById");
		Session session = getCurrentSession();
		StudentModel student = session.get(StudentModel.class, id);
		session.close();

		return student;
	}
	
}
