package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.model.EmployeeFile;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    List<Employee> getEmployeesByLastName(String lastName);
    
    void addNewEmployee(Employee employee);
    
    Integer removeEmployee(int employeeId);
    
    Employee getEmployeesById(int employeeId);
	
	void addNewContact(int employeeId, String contactType, String contactValue);
	
	void addFileToEmployee(int employeeId, EmployeeFile file);
	
	List<EmployeeFile> getFiles(int employeeId);
}
