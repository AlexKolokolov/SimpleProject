package org.kolokolov.simpleproject.service;

import java.util.List;

import org.kolokolov.simpleproject.dao.EmployeeDAO;
import org.kolokolov.simpleproject.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
    @Autowired
    @Qualifier("hibernateEmployeeDAO")
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

    public void removeEmployee(String id) {
        employeeDAO.removeEmployee(id);

    }

    public Employee getEmployeeById(String id) {
        return employeeDAO.getEmployeesById(id);
    }

	public void addNewContactToEmploye(String employeeId, String contactType, String contactValue) {
		employeeDAO.addNewContact(employeeId, contactType, contactValue);
	}
}
