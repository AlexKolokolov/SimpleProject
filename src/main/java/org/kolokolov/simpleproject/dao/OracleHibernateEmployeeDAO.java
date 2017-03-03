package org.kolokolov.simpleproject.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.model.EmployeeFile;
import org.kolokolov.simpleproject.model.EmployeeStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OracleHibernateEmployeeDAO implements EmployeeDAO {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	private DataSource dataSource;

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
		return session.createQuery("FROM Employee WHERE lastName = :lastName", Employee.class)
				.setParameter("lastName", lastName).getResultList();
	}
	
	@Override
	@Transactional
	public List<Employee> getEmployeesOfDepartment(int departmentId) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Employee WHERE department.id = :departmentId", Employee.class)
				.setParameter("departmentId", departmentId).getResultList();
	}

	@Override
	@Transactional
	public int addNewEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		return (int) session.save(employee);
	}

	@Override
	public void removeEmployee(int id) {
		logger.debug("removeEmployee runs with parameter: id = " + id);
		try (Connection connection = dataSource.getConnection();
				CallableStatement statement = connection
						.prepareCall("BEGIN emp_manage.del_emp(:id, :errorCode); END;")) {
			statement.setInt("id", id);
			statement.registerOutParameter("errorCode", java.sql.Types.INTEGER);
			statement.executeUpdate();
			Integer result = statement.getInt("errorCode");
			logger.debug("removeEmployee returned: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public Employee getEmployeesById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Employee.class, id);
	}

	@Override
	@Transactional
	public void addNewContact(int employeeId, String contactType, String contactValue) {
		Session session = sessionFactory.getCurrentSession();
		Employee employee = session.get(Employee.class, employeeId);
		employee.addContact(contactType, contactValue);
		session.persist(employee);
	}

	@Override
	@Transactional
	public List<EmployeeFile> getFiles(int employeeId) {
		Session session = sessionFactory.getCurrentSession();
		Employee employee = session.get(Employee.class, employeeId);
		return employee.getEmployeeFiles();
	}

	@Override
	@Transactional
	public void persistEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		Employee persistedEmployee = session.get(Employee.class, employee.getId());
		persistedEmployee.setFirstName(employee.getFirstName());
		persistedEmployee.setLastName(employee.getLastName());
		persistedEmployee.setDepartment(employee.getDepartment());
		session.persist(persistedEmployee);
	}

	@Override
	@Transactional
	public Employee getDepartmentChief(int departmnetId) {
		Session session = sessionFactory.getCurrentSession();
		return (Employee) session.createQuery("FROM Employee WHERE department.id = :depId AND chief IS NULL")
				.setParameter("depId", departmnetId).getSingleResult();
	}

	@Override
	public List<Employee> getSubordinates(Employee employee) {
		logger.debug("Getting subordinates of " + employee);
		List<Employee> resultList = new ArrayList<>();
		String query = "SELECT employee_id, first_name, last_name FROM employee WHERE department_id = ? START WITH chief_id = ? CONNECT BY PRIOR employee_id = chief_id";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, employee.getDepartment().getId());
			statement.setInt(2, employee.getId());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Employee subordinate = new Employee();
				subordinate.setId(resultSet.getInt(1));
				subordinate.setFirstName(resultSet.getString(2));
				subordinate.setLastName(resultSet.getString(3));
				resultList.add(subordinate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	@Override
	public List<Employee> getChiefs(Employee employee) {
		logger.debug("Getting subordinates of " + employee);
		List<Employee> resultList = new ArrayList<>();
		String query = "SELECT employee_id, first_name, last_name FROM employee WHERE department_id = ? START WITH employee_id = ? CONNECT BY employee_id = PRIOR chief_id";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, employee.getDepartment().getId());
			statement.setInt(2, employee.getChief().getId());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Employee subordinate = new Employee();
				subordinate.setId(resultSet.getInt(1));
				subordinate.setFirstName(resultSet.getString(2));
				subordinate.setLastName(resultSet.getString(3));
				resultList.add(subordinate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	@Override
	public List<EmployeeStatistic> getEmployeeStatistics() {
	    // TODO Auto-generated method stub
	    return null;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
