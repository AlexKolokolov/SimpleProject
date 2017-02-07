package org.kolokolov.simpleproject.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ManagedBean
public class EmployeeDataController {
    
    private static Logger logger = LogManager.getLogger();
    
    @Autowired
    private EmployeeService employeeService;

    public List<Employee> getEmployees() {
        logger.debug("getEmployees method runs");
        return employeeService.getAllEmployees();
    }
}
