package com.mec.video.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mec.video.model.VersionModel;

@Component
public class VersionDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}
	
	public VersionDao() {
	}
	
	public long getVersion(String table) {
		Session session = getCurrentSession();
		VersionModel version = session.get(VersionModel.class, table);
		session.close();
		return version == null ? -1 : version.getVersion();
	}
	
	public long updateVersion(String table) {
		VersionModel version = new VersionModel();
		version.setId(table);
		version.setVersion(System.currentTimeMillis());
		
		Session session = getCurrentSession();
		session.update(version);
		session.close();
		
		return version.getVersion();
	}
	
}
