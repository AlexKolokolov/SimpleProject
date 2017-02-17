package org.kolokolov.simpleproject.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kolokolov.simpleproject.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.internal.OracleTypes;

@Repository
public class OracleHibernateDepartmentDAO implements DepartmentDAO {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public OracleHibernateDepartmentDAO() {
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
		String query = "{ ? = call DEP_MANAGE.F_GET_EMPTY_DEPS() }";
		List<Department> emptyDepartments = new ArrayList<>();
		logger.debug("getEmptyDepartments method runs");
		try (Connection connection = dataSource.getConnection();
			OracleCallableStatement statement = (OracleCallableStatement) connection.prepareCall(query)) {
			statement.registerOutParameter(1, OracleTypes.ARRAY, "DEP_MANAGE.DEP_TABLE_T");
			statement.execute();
			ResultSet rs = (ResultSet) statement.getObject(1);
			while (rs.next()) {
				Department department = new Department(rs.getInt("department_id"), rs.getString("name"));
				emptyDepartments.add(department);
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
