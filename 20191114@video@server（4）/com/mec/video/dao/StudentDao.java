package com.mec.video.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mec.video.model.StudentVideoModel;

@Component
@Transactional
public class StudentDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public StudentDao() {
	}
	
	public Session getSession() {
		return sessionFactory.openSession();
	}

	public StudentVideoModel getStudentById(String id) {
		Session session = getSession();
		StudentVideoModel stu = session.get(StudentVideoModel.class, id);
		session.close();
		
		return stu;
	}
	
}

/*
 * 数据库连接池：数据库必须先连接，即，Connection con = ...(这里要提供url, user和password)
 * 数据库的连接，其实是对数据库服务器的连接；任何服务器能够同时连接的客户端数量都是有限的；
 * 因此，数据库服务器能同时建立的连接数量也是有限的；
 * 数据库连接操作本身非常耗时、耗内存，是代价非常高的操作；若频繁地连接、断开数据库服务器，将会
 * 极大地影响数据库服务器的访问效率！
 * 所以，通过数据库连接池(与线程连接池功能很类似)可以提高效率，增强数据库管理能力。
 * 常用的数据库连接池产品：
 * c3p0
 * */
