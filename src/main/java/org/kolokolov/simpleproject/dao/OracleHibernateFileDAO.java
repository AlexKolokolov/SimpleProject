package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kolokolov.simpleproject.model.EmployeeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OracleHibernateFileDAO implements FileDAO {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<EmployeeFile> getEmployeeFiles(int employeeId) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM EmployeeFile WHERE EmployeeFile.employee.id = :id", EmployeeFile.class).setParameter("id", employeeId).getResultList();
	}

	@Override
	@Transactional
	public EmployeeFile getFile(int fileId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(EmployeeFile.class, fileId);
	}
	
	@Override
	@Transactional
	public void saveFile(EmployeeFile file) {
		logger.debug("saveFile method runs");
		Session session = sessionFactory.getCurrentSession();
		session.save(file);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
