package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.kolokolov.simpleproject.model.Employee;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    List<Employee> getEmployeesByLastName(String lastName);
    
    void addNewEmployee(Employee employee);
    
    void removeEmployee(String id);
    
    Employee getEmployeesById(String id);
}
