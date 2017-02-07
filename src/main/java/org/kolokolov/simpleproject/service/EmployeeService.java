package org.kolokolov.simpleproject.service;

import java.util.List;

import org.kolokolov.simpleproject.dao.EmployeeDAO;
import org.kolokolov.simpleproject.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeDAO employeeDAO;
    
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    
    public void addNewEmployee(Employee employee) {
        employee.setId(employeeDAO.getNextId());
        employeeDAO.addNewEmployee(employee);
    }
}
