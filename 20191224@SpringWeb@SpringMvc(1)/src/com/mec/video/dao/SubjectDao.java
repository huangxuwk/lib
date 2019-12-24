package com.mec.video.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mec.video.IMecInfoTableName;
import com.mec.video.model.SubjectModel;

@Component
public class SubjectDao {
	private long version;
	private List<SubjectModel> subjectList;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private VersionDao versionDao;
	
	public SubjectDao() {
	}
	
	private Session getCurrentSession() {
		return sessionFactory.openSession();
	}

	public long getVersion() {
		return version;
	}

	@SuppressWarnings("unchecked")
	public List<SubjectModel> getBaseSubjectList() {
		long currentVersion = version;
		synchronized (SubjectDao.class) {
			version = versionDao.getVersion(IMecInfoTableName.BASE_SUBJECT_INFO);
		}
		if (version != currentVersion) {
			synchronized (SubjectDao.class) {
				if (version != currentVersion) {
					Session session = getCurrentSession();
					subjectList = 
							session.createQuery("FROM SubjectModel").getResultList();
					version = currentVersion;
					session.close();
				}
			}
		}
		return subjectList;
	}
	
	public void saveBaseSubject(SubjectModel subject) {
		Session session = getCurrentSession();
		synchronized (SubjectDao.class) {
			session.save(subject);
			versionDao.updateVersion(
					IMecInfoTableName.BASE_SUBJECT_INFO);
		}
		session.close();
	}
	
	public void updateBaseSubject(SubjectModel subject) {
		Session session = getCurrentSession();
		synchronized (SubjectDao.class) {
			session.update(subject);
			versionDao.updateVersion(
					IMecInfoTableName.BASE_SUBJECT_INFO);
		}
		session.close();
	}
	
}
