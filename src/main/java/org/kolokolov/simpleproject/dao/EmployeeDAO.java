package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.kolokolov.simpleproject.model.Employee;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    List<Employee> getEmployeesByLastName(String lastName);
    
    void addNewEmployee(Employee employee);
    
    Integer removeEmployee(String id);
    
    Employee getEmployeesById(String id);
	
	void addNewContact(String employeeId, String contactType, String contactValue);
	
	void addFileToEmployee(String id, byte[] bytes);
}
