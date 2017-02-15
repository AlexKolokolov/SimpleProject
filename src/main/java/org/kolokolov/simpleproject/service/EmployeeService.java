package org.kolokolov.simpleproject.service;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.dao.EmployeeDAO;
import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.model.EmployeeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	private static Logger logger = LogManager.getLogger();
    
    @Autowired
    @Qualifier("oracleHibernateEmployeeDAO")
    private EmployeeDAO employeeDAO;
    
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    
    public void addNewEmployee(Employee employee) {
        employeeDAO.addNewEmployee(employee);
    }

    public Integer removeEmployee(String id) {
        return employeeDAO.removeEmployee(Integer.parseInt(id));
    }

    public Employee getEmployeeById(String id) {
        return employeeDAO.getEmployeesById(Integer.parseInt(id));
    }

	public void addNewContactToEmploye(String id, String contactType, String contactValue) {
		employeeDAO.addNewContact(Integer.parseInt(id), contactType, contactValue);
	}

	public void addFileToEmployee(String id, EmployeeFile file) {
		logger.debug("File to save " + file.getName() + ", " + Arrays.toString(file.getData()));
		employeeDAO.addFileToEmployee(Integer.parseInt(id), file);
	}

	public List<EmployeeFile> getFiles(String id) {
		return employeeDAO.getFiles(Integer.parseInt(id));
	}
}
