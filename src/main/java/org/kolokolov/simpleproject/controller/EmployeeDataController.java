package org.kolokolov.simpleproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@ManagedBean
@RequestScope
public class EmployeeDataController {
    
    private static Logger logger = LogManager.getLogger();
    
    @Autowired
    private EmployeeService employeeService;
    
    private String id;

    public List<Employee> getEmployees() {
        logger.debug("getEmployees method runs");
        List<Employee> employees = employeeService.getAllEmployees();
        logger.debug("getEmployees method returns: " + employees);
        if (employees == null) {
        	employees = new ArrayList<>();
        }
        return employees;
    }
    
    public Employee getEmployee() {
    	return employeeService.getEmployeeById(id);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    
}
