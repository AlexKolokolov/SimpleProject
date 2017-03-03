package org.kolokolov.simpleproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class PostgresHibernateEmployeeDAO implements EmployeeDAO {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SessionFactory sessionFactory;

	public PostgresHibernateEmployeeDAO() {
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
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("DELETE FROM Employee WHERE id = :id", Employee.class).setParameter("id", id).executeUpdate();
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
        List<EmployeeStatistic> statistics = new ArrayList<>();
        String query = "SELECT " 
                + "e.first_name, " 
                + "e.last_name, " 
                + "d.name, " 
                + "e.salary, "
                + "round(AVG(e.salary) OVER (), 2), " 
                + "round(AVG(e.salary) OVER (PARTITION BY e.department_id), 2), "
                + "round(AVG(e.salary) OVER (PARTITION BY e.gender), 2), "
                + "round(AVG(e.salary) OVER (PARTITION BY e.gender, e.department_id), 2), "
                + "COUNT(e.employee_id) OVER (PARTITION BY e.gender, e.department_id), "
                + "COUNT(CASE WHEN e.gender = 0 THEN e.employee_id END) OVER (PARTITION BY e.department_id), "
                + "COUNT(CASE WHEN e.gender = 1 THEN e.employee_id END) OVER (PARTITION BY e.department_id), "
                + "COUNT(CASE WHEN e.gender = 0 THEN e.employee_id END) OVER (), "
                + "COUNT(CASE WHEN e.gender = 1 THEN e.employee_id END) OVER () "
                + "FROM employee e "
                + "NATURAL JOIN department d";
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                EmployeeStatistic stat = new EmployeeStatistic();
                stat.setFirstName(resultSet.getString(1));
                stat.setLastName(resultSet.getString(2));
                stat.setDepartment(resultSet.getString(3));
                stat.setSalary(resultSet.getBigDecimal(4));
                stat.setAvgSalary(resultSet.getBigDecimal(5));
                stat.setAvgSalaryInDep(resultSet.getBigDecimal(6));
                stat.setAvgSalaryByGender(resultSet.getBigDecimal(7));
                stat.setAvgSalaryInDepByGender(resultSet.getBigDecimal(8));
                stat.setSameGenderInDep(resultSet.getInt(9));
                stat.setMenInDep(resultSet.getInt(10));
                stat.setWomenInDep(resultSet.getInt(11));
                stat.setMenTotal(resultSet.getInt(12));
                stat.setWomenTotal(resultSet.getInt(13));
                statistics.add(stat);
            }
        } catch (Exception e) {
            statistics = null;
            e.printStackTrace();
        }
        return statistics;
    }

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
