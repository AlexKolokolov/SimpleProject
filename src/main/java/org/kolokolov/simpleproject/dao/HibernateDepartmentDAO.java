package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kolokolov.simpleproject.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateDepartmentDAO implements DepartmentDAO {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private SessionFactory sessionFactory;

	
	
	public HibernateDepartmentDAO() {
		logger.debug("HibernateDepartmentDAO instantiated");
	}

	@Override
	@Transactional
	public List<Department> getAllDepartments() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Department", Department.class).getResultList();
	}

	@Override
	public List<Department> getEmptyDepartments() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Department", Department.class).getResultList();
	}

	@Override
	public Department getDepartmentById(String id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Department.class, Integer.parseInt(id));
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
