package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kolokolov.simpleproject.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateDepartmentDAO implements DepartmentDAO {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	@Qualifier("sessionFactory")
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
	@Transactional
	public List<Department> getEmptyDepartments() {
		String query = "SELECT d.department_id, d.name FROM department d LEFT JOIN employee e ON e.department_id = d.department_id WHERE e.department_id IS NULL";
		Session session = sessionFactory.getCurrentSession();
		return (List<Department>) session.createSQLQuery(query).addEntity(Department.class).getResultList();
	}

	@Override
	@Transactional
	public Department getDepartmentById(String id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Department.class, Integer.parseInt(id));
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
