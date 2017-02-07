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
    
    private int id;
    private String firstName;
    private String lastName;
    
    private Employee employee;
    
    public String getMessage() {
        String msg;
        if (firstName == null || lastName == null) {
            msg = "";
        } else {
            employee = new Employee(id, firstName, lastName);
            employeeService.addNewEmployee(employee);
            msg = String.format("New employee %s has been added", employee);
        }
        return msg;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
