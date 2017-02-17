package org.kolokolov.simpleproject.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.dao.EmployeeDAO;
import org.kolokolov.simpleproject.dao.HistoryDAO;
import org.kolokolov.simpleproject.model.Action;
import org.kolokolov.simpleproject.model.Department;
import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	private static Logger logger = LogManager.getLogger();
    
    @Autowired
    @Qualifier("oracleHibernateEmployeeDAO")
    private EmployeeDAO employeeDAO;
    
    @Autowired
    private HistoryDAO historyDAO;
    
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void setHistoryDAO(HistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}

	public void addNewEmployee(Employee employee) {
        int generatedId = employeeDAO.addNewEmployee(employee);
        String eventDescription = String.format("Employee %s %s has been hired", employee.getFirstName(), employee.getLastName());
        logger.debug("generated id = " + generatedId);
        employee.setId(generatedId);
        Action action = historyDAO.getAction(1);
        Event event = new Event();
        event.setDescription(eventDescription);
        event.setAction(action);
        event.setDate(new Date());
        event.setEmployee(employee);
        historyDAO.addEvent(event);
    }

    public Integer removeEmployee(int id) {
    	Integer errorCode = employeeDAO.removeEmployee(id);
    	if (errorCode != null && errorCode == 0) {
    		Employee employee = employeeDAO.getEmployeesById(id);
    		String eventDescription = String.format("Employee %s %s has been fired", employee.getFirstName(), employee.getLastName());
    		Event event = new Event();
    		event.setAction(historyDAO.getAction(3));
    		event.setDescription(eventDescription);
    		event.setEmployee(employee);
    		event.setDate(new Date());
    		historyDAO.addEvent(event);
    	}
        return errorCode;
    }
    
    public List<Employee> getSubordinates(Employee employee) {
    	return employeeDAO.getSubordinates(employee);
    }

    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeesById(id);
    }

	public void addNewContactToEmploye(int id, String contactType, String contactValue) {
		employeeDAO.addNewContact(id, contactType, contactValue);
	}

	public void saveEmployee(Employee employee) {
		employeeDAO.persistEmployee(employee);
		
	}

	public Employee getDepartmetChief(Department department) {
		return employeeDAO.getDepartmentChief(department.getId());
	}
}
