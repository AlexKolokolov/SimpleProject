package org.kolokolov.simpleproject.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ManagedBean
public class EmployeeDataController {
    
    @Autowired
    private EmployeeService employeeService;

    public List<Employee> getEmployees() {
        System.out.println("---getEmployees---");
        return employeeService.getAllEmployees();
    }
}
