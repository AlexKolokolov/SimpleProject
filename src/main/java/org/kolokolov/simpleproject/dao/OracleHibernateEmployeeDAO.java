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
public class OracleHibernateEmployeeDAO implements EmployeeDAO {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public OracleHibernateEmployeeDAO() {
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
	public Integer removeEmployee(String id) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("removeEmployee runs with parameter: id = " + id);
		Integer result = (Integer) session.createSQLQuery("BEGIN emp_manage.del_emp(:id); END;").setParameter("id", Integer.parseInt(id)).addEntity(Integer.class).getFirstResult();
		logger.debug("removeEmployee returned: " + result);
		return result;
	}

	@Override
	@Transactional
	public Employee getEmployeesById(String id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Employee.class, Integer.parseInt(id));
	}
	
	@Override
	@Transactional
	public void addNewContact(String employeeId, String contactType, String contactValue) {
		Session session = sessionFactory.getCurrentSession();
		Employee employee = session.get(Employee.class, Integer.parseInt(employeeId));
		employee.addContact(contactType, contactValue);
		session.persist(employee);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
