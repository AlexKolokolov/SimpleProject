package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kolokolov.simpleproject.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateEmployeeDAO implements EmployeeDAO {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public HibernateEmployeeDAO() {
		logger.debug("HibernateEmployeeDAO instantiated");
	}

	@Override
	@Transactional
	public List<Employee> getAllEmployees() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Employee", Employee.class).getResultList();
	}

	@Override
	@Transactional
	public List<Employee> getEmployeesByLastName(String lastName) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Employee WHERE lastName = :lastName", Employee.class).setParameter("lastName", lastName).getResultList();
	}

	@Override
	@Transactional
	public void addNewEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		session.save(employee);
	}

	@Override
	@Transactional
	public void removeEmployee(String id) {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("DELETE Employee WHERE id = :id").setParameter("id", Integer.parseInt(id)).executeUpdate();
	}

	@Override
	@Transactional
	public Employee getEmployeesById(String id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Employee.class, Integer.parseInt(id));
	}
	
	@Override
	public void persistEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(employee);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
