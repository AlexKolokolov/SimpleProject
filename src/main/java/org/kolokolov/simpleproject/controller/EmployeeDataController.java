package org.kolokolov.simpleproject.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.model.EmployeeFile;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ManagedBean
public class EmployeeDataController {
    
    private Employee employee;
    
    @Autowired
    private EmployeeService employeeService;
    
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees;
    }
    
    public Employee getEmployee() {
    	return employee;
    }
    
    public Map<String,String> getContacts() {
    	return employeeService.getEmployeeById(employee.getId()).getContacts();
    }
    
    public List<EmployeeFile> getEmployeeFiles() {
    	return employeeService.getEmployeeById(employee.getId()).getEmployeeFiles();
    }

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}   
}
