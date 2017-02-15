package org.kolokolov.simpleproject.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ManagedBean
public class EmployeeDataController {
	
    private String employeeId;
    
    @Autowired
    private EmployeeService employeeService;
    
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees;
    }
    
    public Employee getEmployee() {
    	return employeeService.getEmployeeById(employeeId);
    }
    
    public Map<String,String> getContacts() {
    	return employeeService.getEmployeeById(employeeId).getContacts();
    }

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}   
}
