package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.model.EmployeeFile;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    List<Employee> getEmployeesByLastName(String lastName);
    
    int addNewEmployee(Employee employee);
    
    Integer removeEmployee(int employeeId);
    
    Employee getEmployeesById(int employeeId);
	
	void addNewContact(int employeeId, String contactType, String contactValue);
	
	List<EmployeeFile> getFiles(int employeeId);
	
	void persistEmployee(Employee employee);
}
