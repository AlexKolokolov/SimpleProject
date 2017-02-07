package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.kolokolov.simpleproject.model.Employee;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    List<Employee> getEmployeesByLastName();
    
    void addNewEmployee(Employee employee);
    
    int getNextId();
}
