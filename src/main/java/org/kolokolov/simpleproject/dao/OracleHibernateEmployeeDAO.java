package org.kolokolov.simpleproject.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kolokolov.simpleproject.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OracleHibernateEmployeeDAO implements EmployeeDAO {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("sessionFactory")
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
	public Integer removeEmployee(String id) {
		logger.debug("removeEmployee runs with parameter: id = " + id);
		try (Connection connection = dataSource.getConnection();
			CallableStatement statement = connection.prepareCall("BEGIN emp_manage.del_emp(:id, :errorCode); END;")) {
			statement.setInt("id", Integer.parseInt(id));
			statement.registerOutParameter("errorCode", java.sql.Types.INTEGER);
			statement.executeUpdate();
			Integer result = statement.getInt("errorCode");
			logger.debug("removeEmployee returned: " + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
