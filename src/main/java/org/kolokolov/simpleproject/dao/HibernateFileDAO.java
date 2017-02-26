package org.kolokolov.simpleproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kolokolov.simpleproject.model.EmployeeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateFileDAO implements FileDAO {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;

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
	
	@Override
	public Map<Integer, String> getFileDescriptions(int employeeId) {
		String query = "SELECT employee_file_id, file_name FROM employee_file WHERE employee_id = ?";
		Map<Integer,String> descriptions = new LinkedHashMap<>();
		try (Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, employeeId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				descriptions.put(resultSet.getInt(1),resultSet.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return descriptions;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
