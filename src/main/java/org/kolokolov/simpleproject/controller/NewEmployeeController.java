package org.kolokolov.simpleproject.controller;

import javax.faces.bean.ManagedBean;

import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@ManagedBean
@RequestScope
public class NewEmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    private String firstName;
    private String lastName;
    
    private Employee employee;
    
    public String getMessage() {
        String msg;
        if (employee == null) {
            msg = "";
        } else {
            msg = String.format("New employee %s has been added", employee);
        }
        return msg;
    }
    
    public void addNewEmployee() {
    	employee = new Employee(firstName, lastName);
        employeeService.addNewEmployee(employee);
    }
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public EmployeeService getEmployeeService() {
        return employeeService;
    }
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
