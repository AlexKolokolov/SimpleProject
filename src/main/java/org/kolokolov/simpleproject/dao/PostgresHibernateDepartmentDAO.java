package org.kolokolov.simpleproject.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kolokolov.simpleproject.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PostgresHibernateDepartmentDAO implements DepartmentDAO {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SessionFactory sessionFactory;

	public PostgresHibernateDepartmentDAO() {
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
		List<Department> emptyDepartments = new ArrayList<>();
		String query = "SELECT d.department_id, d.name FROM department d LEFT JOIN employee e ON d.department_id = e.department_id WHERE e.department_id IS NULL";
		try (Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				emptyDepartments.add(new Department(resultSet.getInt(1), resultSet.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return emptyDepartments;
	}

	@Override
	@Transactional
	public Department getDepartmentById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Department.class, id);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}