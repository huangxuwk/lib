package com.mec.video.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface ISession {

	default Session getSession(SessionFactory sessionFactory) {
		return sessionFactory.openSession();
	}
	
}
